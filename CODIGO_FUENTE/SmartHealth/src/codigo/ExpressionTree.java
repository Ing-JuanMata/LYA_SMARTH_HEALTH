/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.util.ArrayList;
import codigo.Token;
import codigo.Identificador;
import codigo.Identificador;
import codigo.Token;
/**
 *
 * @author memos
 */
public class ExpressionTree {
    
  private Node<Token> root,current;
	private boolean flag = false;
	private int counter = 0;
	public String result = "", temp = "";
	private Identificador token;
	private ArrayList<Identificador> symbols;
	private ArrayList<Cuadruple> cuadruples = new ArrayList<>();

	public ExpressionTree(){}

	public ExpressionTree(Identificador t,ArrayList<Identificador> symb){
		token = t; symbols = symb;
	}
	public ArrayList<Cuadruple> retList(){
		return cuadruples;
	}
	public void add(Token t){
		current = insert(current,t);
		flag = false;
	}
	
	public Node<Token> insert(Node<Token> current,Token t){
		Node<Token> newNode, aux;
		if( current == null){
			newNode = new Node<Token>(t);
			if( root != null){
				newNode.left = root;
				root.parent = newNode;
			}
			root = newNode;
			
			return newNode;
		}
		newNode = new Node<Token>(t);
		int currentPriority = priority(current), 
				newPriority = priority(newNode);
		if( currentPriority >= newPriority){
			aux = insert(current.parent,t);
			if( flag ){
				current.parent = aux;
				aux.left = current;
			}
			
			current = aux;
			flag=false; 
		}else{
			newNode.parent = current;
			current.right = newNode;
			current = newNode;
			flag = true;
		}
		
		return current;
	}

	private int priority(Node<Token> n){
		int val = -1;
		Token t = (Token) n.data;
		if (t.tipo  == "<Numero>") {
                    if(t.tipo == "<identificador>"){
                        val = 3;
                        //break;
                    }
                } 
		if(t.tipo == "<Aritmetico>"){
                    if( t.lexema.matches("(\\*|/)") ){
				val = 2;
			}else{
				val = 1;
			}
			
                }	
			
		
		return val;
	}
	private boolean assignment(){
		if( root.left == null && root.right == null )
			return true;
		return false;
	}
	public String generateCuadruple(Node<Token> node) {
	     if (node != null) {
	    	 String value1,value2,vv1=null,vv2=null;
	    	 value1 = generateCuadruple(node.left);
	         value2 = generateCuadruple(node.right);
                 
	         if( node.data.tipo == "<Aritmetico>"){
	        	 int distance = 8;
	        	 counter++;
	        	 if( !value1.matches("(T\\d+|\\d+)")){
	        		 vv1 = retValue(value1);
	        	 }
	        	 if( !value2.matches("(T\\d+|\\d+)")){
	        		 vv2 = retValue(value2);
	        	 }
	        	 
	        	 if( node.parent == null){
	        		 result += String.format("%5s %-"+(distance+2)+"s %-"+(distance+2)
	        				 +"s %-"+(distance+4)+"s %-"+(distance+0)+"s %n","",node.data.lexema,value1,value2,token.getNombre());
	        		 cuadruples.add(
	        				 new Cuadruple(token.getNombre(), node.data.lexema, value1, value2, vv1, vv2)	
	        				 );
	        	 }else{
	        		 
	        	 	result += String.format("%5s %-"+(distance+2)+"s %-"+(distance+2)+"s %-"
	        		 +(distance+4)+"s %-"+(1+0)+"s %n","",node.data.lexema,value1,value2,"T"+counter);
	        	 	cuadruples.add(
	        				 new Cuadruple("T"+counter, node.data.lexema, value1, value2, vv1, vv2)	
	        				 );
	        	 }
	        	 return "T"+(counter);
	         }else{
	        	 if( assignment() )
	        		 result += String.format("%8s %s %4s %n %n", "T1",":=",node.data.tipo);
	        	 return node.data.lexema;
	         }
	         
	         
	     }
	     return "";
	 }
	
	 public String solve(){
	 	String resultValue = "";
		 switch(token.getTipo()){
		 	case "int":
				resultValue = String.valueOf((int)solveGen(root));
				break;
		 	case "double":
				resultValue = String.valueOf(solveGen(root));
				break;
		 	case "float":
				resultValue = String.valueOf(solveGen(root))+"f";
				break;
		 }
		 	generateCuadruple(root);
			result += String.format("%8s %s %4s %n",token.getNombre(),":=",resultValue);
		 return resultValue;
	 }
	
	 public double solveGen(Node<Token> node) {
	     if (node != null) {
	    	 double value1,value2;
	    	 value1 = solveGen(node.left);
	         value2 = solveGen(node.right);
	         if( node.data.tipo == "<Aritmetico>"){
	         	char c = node.data.lexema.charAt(0);
	         	double res = 0;
	        	 switch(c){
	        	 	case '+': res = value1 + value2; break;
	        	 	case '-': res = value1 - value2; break;
	        	 	case '*': res = value1 * value2; break;
	        	 	case '/': res = value1 / value2; break;
	        	 }
	        	 return res;
	         }else if( node.data.tipo == "<identificador>")
	        	 return Double.parseDouble(retValue(node.data.lexema));
	         else
	        	 return Double.parseDouble(node.data.lexema);
	         
	     }
	     return 0;
	 }
	
	 private String retValue(String id){
		 for (Identificador identifier : symbols) {
			if( identifier.getNombre().equals(id) )
				return identifier.getValor();
		}
		 return null;
	 }
	class Node<Token>{
		Token data;
		Node<Token> parent,right,left;
		
		public Node(Token val){ data = val; }
	}
}
