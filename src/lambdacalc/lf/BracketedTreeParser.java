package lambdacalc.lf;

import java.util.*;
import lambdacalc.logic.SyntaxException;

/**
 * Parses bracketed tree expressions into LFNode trees.
 *
 * A bracketed tree expression is:
 *
 * S -> NODE
 * NODE -> NONTERMINAL | TERMINAL
 * NONTERMINAL -> `[` (.LABEL)? (=RULE;)? NODE+ `]`
 * TERMINAL -> LABEL(=EXPR;)?
 * LABEL => a text string, no white space, optionally followed by an underscore
 *          and an integral value (its index)
 * RULE => `fa`     (i.e. function application)
 * EXPR -> a predicate logic expression parsed by lambdacalc.logic.ExpressionParser.
 *
 * Implementation notes:
 * This is a simple recursive descent parser. One pass is made over the input
 * string from left to right. A state variable (parseMode) holds onto what the
 * parser is currently doing, and a stack holds the path of nonterminals from
 * the root to the node currently being processed.
 */
public class BracketedTreeParser {

    public static Nonterminal parse(String tree) throws SyntaxException {
        // A quick and dirty recurive cfg parser.
        
        // A stack of the nonterminal nodes from the root down to (but not
        // including) the nonterminal node currently being processed.
        Stack stack = new Stack();
        
        // The nonterminal node currently being processed. null if we have not
        // yet encountered the root node.
        Nonterminal curnode = null;
        
        // The terminal node currently being processed while parseMode==2,
        // i.e. when we're reading a terminal label. null if parseMode!=2.
        LexicalTerminal curterminal = null;
        
        // The node that we're currently setting the index of.
        LFNode curNodeForIndex = null;
        
        // The current state of the parser.
        int parseMode = 0;
        // 0 -> looking for a node:
        //      [ indicates start of nonterminal
        //      white space is skipped
        //      ] indicates end of node, pop the stack to get current node's parent
        //      . indicates start of nonterminal label (like qtree)
        //      = indicates the start of a name of a composition rule to use
        //        for this nonterminal, terminated by a semicolon
        //      anything else indicates start of terminal
        // 1 -> reading a nonterminal label:
        //      space indicates end
        //      [, ], = indicate end, must back-track
        //      _ indicates start of index
        //      everything else gets added to label
        // 2 -> reading terminal label:
        //      space and brackets indicate end
        //      = is the start of a lambda expression to
        //        assign as the lexical entry, up until the next semicolon
        //      _ indicates start of index
        //      everything else gets added to the label
        // 3 -> reading the index of curNode
        //      a non-digit indicates the end, must back track so it's
        //       parsed in the right state
        //      all digits go into the index
        
        for (int i = 0; i < tree.length(); i++) {
        
            char c = tree.charAt(i);
            
            if (parseMode == 0) {
                // Looking for a node.
                switch (c) {
                case '[':
                    // start a nonterminal
                    Nonterminal nt = new Nonterminal();
                    if (curnode != null) {
                        curnode.addChild(nt);
                        stack.push(curnode);
                    }
                    curnode = nt;
                    break;

                case '.':
                    if (curnode == null)
                        throw new SyntaxException("A period cannot appear before the starting open-bracket of the root node.", i);
                    if (curnode.size() != 0)
                        throw new SyntaxException("A period to start a nonterminal node label cannot appear after a child node.", i);
                    parseMode = 1;
                    break;      
                                                            
                case ']':
                    if (curnode == null)
                        throw new SyntaxException("A close-bracket cannot appear before the starting open-bracket of the root node.", i);
                    if (curnode.size() == 0)
                        throw new SyntaxException("A nonterminal node must have at least one child.", i);
                    if (stack.size() > 0) {
                        curnode = (Nonterminal)stack.pop();
                    } else {
                        // We're at the end of the root node.
                        // Verify that only white space follows.
                        for (int j = i + 1; j < tree.length(); j++) {
                            if (!Character.isWhitespace(tree.charAt(j))) {
                                if (tree.charAt(j) == ']')
                                    throw new SyntaxException("There are too many close-brackets at the end of the tree.", j);
                                else
                                    throw new SyntaxException("Nothing can follow the end of the root element.", j);
                            }
                        }
                        return curnode;
                    }
                    break;
                    
                case '=':
                    if (curnode == null)
                        throw new SyntaxException("An equal sign cannot appear before the starting open-bracket of the root node.", i);
                    if (curnode.size() != 0)
                        throw new SyntaxException("An equal sign to start a nonterminal composition rule cannot appear after a child node.", i);
                    // scan for ending semicolon
                    int semi = tree.indexOf(';', i);
                    if (semi == -1)
                        throw new SyntaxException("A semicolon must terminate the end of a composition rule being assigned to a nonterminal node with '='.", i);
                    String rulename = tree.substring(i+1, semi);
                    if (rulename.equals("fa"))
                        curnode.setCompositionRule(FunctionApplicationRule.INSTANCE);
                    else
                        throw new SyntaxException("The name '" + rulename + "' is invalid", i);
                    i = semi; // resume from next position (i is incremented at end of iteration)
                    break;
                    
                case LFNode.INDEX_SEPARATOR:
                    parseMode = 3;
                    curNodeForIndex = curnode;
                    break;
                        
                case ' ':
                    // skip
                    break;
                    
                default:
                    // this is the start of a terminal
                    if (curnode == null)
                        throw new SyntaxException("An open bracket for the root node must appear before any other text.", i);
                    curterminal = new LexicalTerminal(); 
                    // we always start by assuming that the current terminal is
                    // a lexical terminal; if necessary, we convert it later
                    // (in finishTerminal)
                    curterminal.setLabel(String.valueOf(c));
                    parseMode = 2;
                    break;
                }
                
            } else if (parseMode == 1) {
                // Reading the label of the nonterminal.
                switch (c) {
                    case ' ':
                        parseMode = 0;
                        break;
                
                    case ']':
                    case '[':
                    case '=':
                        parseMode = 0;
                        i--; // back track so they are parsed in parseMode 0
                             // (i gets incremented at end of iteration, so we
                             // set it back one here so that on next iteration
                             // we haven't moved)
                        break;
                        
                    default:
                        String curlabel = curnode.getLabel();
                        if (curlabel == null)
                            curlabel = "";
                        curnode.setLabel(curlabel + c);
                        break;
                }
            
            } else if (parseMode == 2) {
                // Reading the label of a terminal.
                // Space and brackets indicate end. We'll back track in all
                // cases so they are parsed in parseMode 0.
                
                switch (c) {
                    case ' ':
                    case ']':
                    case '[':
                        finishTerminal(curnode, curterminal);
                        parseMode = 0;
                        curterminal = null;
                        i--; // back track so they are parsed in parseMode 0
                        break;
                    
                    case '=':
                        // scan for ending semicolon
                        int semi = tree.indexOf(';', i);
                        if (semi == -1)
                            throw new SyntaxException("A semicolon must terminate the end of a predicate logic expression being assigned to a terminal node with '='.", i);
                        String lambda = tree.substring(i+1, semi);
                        try {
                            lambdacalc.logic.ExpressionParser.ParseOptions popts = new lambdacalc.logic.ExpressionParser.ParseOptions();
                            popts.ASCII = true;
                            popts.singleLetterIdentifiers = false;
                            lambdacalc.logic.Expr expr = lambdacalc.logic.ExpressionParser.parse(lambda, popts);
                            curterminal.setMeaning(expr);
                        } catch (lambdacalc.logic.SyntaxException ex) {
                            throw new SyntaxException("The lambda expression being assigned to '" + curterminal.getLabel() + "' is invalid: " + ex.getMessage(), i);
                        }
                        finishTerminal(curnode, curterminal);
                        i = semi; // resume from next position (i is incremented at end of iteration)
                        parseMode = 0; // reading of terminal label is complete
                        break;
                        
                    case LFNode.INDEX_SEPARATOR:
                        curNodeForIndex = finishTerminal(curnode, curterminal);
                        parseMode = 3;
                        curterminal = null;
                        break;
                        
                    default:
                        curterminal.setLabel(curterminal.getLabel() + c);
                        break;
                }
                
            } else if (parseMode == 3) {
                // Reading the index of a node.
                if (Character.isDigit(c)) {
                    int idx = Integer.valueOf(String.valueOf(c)).intValue();
                    if (curNodeForIndex.getIndex() == -1)
                        curNodeForIndex.setIndex(idx);
                    else // perform arithmetic to do string concatenation
                        curNodeForIndex.setIndex(curNodeForIndex.getIndex() * 10 + idx);
                } else {
                    parseMode = 0;
                    i--; // make sure to re-read this character on the next iteration
                }
            }
        
        }
        
        // We return successfully when we encounter the close bracket of the
        // root node. If we get here, the tree is bad.
        throw new SyntaxException("Not enough close-brackets at the end of the tree.", tree.length() - 1);
        
    }
    
    private static Terminal finishTerminal(Nonterminal parent, Terminal child) {
        // If the terminal label was just an integer,
        // load it as a BareIndex object.
        try {
            int idx = Integer.valueOf(child.getLabel()).intValue();
            child = new BareIndex(idx);
        } catch (NumberFormatException e) {
            // ignore parsing error: it's not a bare index
        }

        // If the terminal label was "t" with an index,
        // load it as a Trace object.
        if ((child.getLabel() != null) 
        && (child.getLabel().equals(Trace.SYMBOL))
        && child.getIndex() != -1) {
            child = new Trace(child.getIndex());
        }
        parent.addChild(child);
        return child;
    }

    // For debugging.
    public static void main(String[] args) throws SyntaxException, MeaningEvaluationException, lambdacalc.logic.TypeEvaluationException {
        Nonterminal root = parse(args[0]);
        System.out.println(root.toString());
        lambdacalc.logic.Expr expr = root.getMeaning();
        System.out.println(expr);
        
        while (true) {
            lambdacalc.logic.Expr.LambdaConversionResult r = expr.performLambdaConversion();
            if (r == null)
                break;
            expr = r.result;
            System.out.println(expr);
        }
    }
}
