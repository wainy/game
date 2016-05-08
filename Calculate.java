
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
 
/**
 * Java Application-calculate
 * @author Nan Song
 * Reference: http://www.oschina.net/code/snippet_149035_15961
 */
public class Calculate extends JFrame {
     
    private String oprecord="";//record the all operate contains number button and operate button
     
    public void recordOp(String str){
        oprecord+=str;
//      System.out.println(oprecord);
    }
    private class Listener implements ActionListener{
         
        public void judgeInit(){
            if(oprecord.length()>3){
                String str=oprecord.substring(oprecord.length()-2,oprecord.length()-1);
                if(str.equals("=")){
                    tmpval="";
                }
            }
        }
         
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btn1){
                recordOp("1");judgeInit();
                val+="1";
                txt.setText(val);
            }
            if(e.getSource()==btn2){
                recordOp("2");judgeInit();
                val+="2";
                txt.setText(val);
            }
            if(e.getSource()==btn3){
                recordOp("3");judgeInit();
                val+="3";
                txt.setText(val);
            }
            if(e.getSource()==btn4){
                recordOp("4");judgeInit();
                val+="4";
                txt.setText(val);
            }
            if(e.getSource()==btn5){
                recordOp("5");judgeInit();
                val+="5";
                txt.setText(val);
            }
            if(e.getSource()==btn6){
                recordOp("6");judgeInit();
                val+="6";
                txt.setText(val);
            }
            if(e.getSource()==btn7){
                recordOp("7");judgeInit();
                val+="7";
                txt.setText(val);
            }
            if(e.getSource()==btn8){
                recordOp("8");judgeInit();
                val+="8";
                txt.setText(val);
            }
            if(e.getSource()==btn9){
                recordOp("9");judgeInit();
                val+="9";
                txt.setText(val);
            }
            if(e.getSource()==btn0){
                if(val!="0"){
                    recordOp("0");
                    val+="0";
                    txt.setText(val);
                }
            }
            if(e.getSource()==btnDot){
                if(val!=""&&!val.contains(".")){
                    recordOp(".");
                    val+=".";
                    txt.setText(val);
                }
            }
            if(e.getSource()==btnAdd){
                op("+");
            }
            if(e.getSource()==btnCut){
                op("-");
            }
            if(e.getSource()==btnMulti){
                op("*");
            }
            if(e.getSource()==btnDiv){
                op("/");
            }
            if(e.getSource()==btnRemdr){
                op("%");
            }
            if(e.getSource()==btnC){
                c();
            }
            if(e.getSource()==btnCE){
                ce();
            }
            if(e.getSource()==btnEq){
                eq();
            }
            if(e.getSource()==btnBsp){
                backspace();
            }
        }
    }
     
    /**
     * backspace button
     */
    public void backspace(){
        if(val!=""){
            if(val.length()==1){//if val's length equals 1
                val="";
            }else{//if not
                val=val.substring(0,val.length()-1);
            }
            txt.setText(val);
        }
    }
     
    /**
     * clean all variable
     */
    public void c(){
        val="";
        tmpval="";
        lastop="";
        txt.setText("");//show empty
    }
     
    /**
     * click the CE button
     */
    public void ce(){
        if(val!=""){//if last input value not empty
            val="";//clean the last input value
            txt.setText("");//clean JTextField
        }
    }
     
    /**
     * click the equals button
     */
    public void eq(){
        recordOp("=");
        if(val.length()>0&&tmpval.length()>0){
            if(lastop.equals("+")){
                tmpval=String.valueOf(Double.valueOf(tmpval)+Double.valueOf(val));//calculate and set value to tmpval
            }else if(lastop.equals("-")){
                tmpval=String.valueOf(Double.valueOf(tmpval)-Double.valueOf(val));
            }else if(lastop.equals("*")){
                tmpval=String.valueOf(Double.valueOf(tmpval)*Double.valueOf(val));
            }else if(lastop.equals("/")){
                tmpval=String.valueOf(Double.valueOf(tmpval)/Double.valueOf(val));
            }else if(lastop.equals("%")){
                tmpval=String.valueOf(Double.valueOf(tmpval)%Double.valueOf(val));
            }
            txt.setText(tmpval);//show in JTextField
            val="";//empty the val
        }
    }
     
    /**
     * click the +,-,*,/,% button
     * @param opstr String clicked operate button's Text
     */
    public void op(String opstr){
        recordOp(opstr);
        lastop=opstr;//record the last operate
        if(val.length()>0){//if input some numbers before
            if(tmpval.length()<1){//if tmpval is empty
                tmpval=val;//set val's value to tmpval
            }else{
                if(opstr.equals("+")){//judge operate
                    tmpval=String.valueOf(Double.valueOf(tmpval)+Double.valueOf(val));
                }else if(opstr.equals("-")){
                    tmpval=String.valueOf(Double.valueOf(tmpval)-Double.valueOf(val));
                }else if(opstr.equals("*")){
                    tmpval=String.valueOf(Double.valueOf(tmpval)*Double.valueOf(val));
                }else if(opstr.equals("/")){
                    tmpval=String.valueOf(Double.valueOf(tmpval)/Double.valueOf(val));
                }else if(opstr.equals("%")){
                    tmpval=String.valueOf(Double.valueOf(tmpval)%Double.valueOf(val));
                }
                txt.setText(tmpval);//show in JTextField
            }
            val="";//empty the val
        }
    }
     
    private static final long serialVersionUID = 2555984474884316451L;
     
    private JFrame frm;
    private JTextField txt;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn0;
    private JButton btnDot;
    private JButton btnAdd;
    private JButton btnCut;
    private JButton btnMulti;
    private JButton btnDiv;
    private JButton btnEq;
    private JButton btnBsp;//cancel the last input character. exp:val=val.subString(0,str.length()-1)
    private JButton btnC;//clear val and tmpval value
    private JButton btnCE;//clear last input value
    private JButton btnRemdr;//take the remainder
     
    private String val="";//now input value
    private String tmpval="";//temporary value
    private String lastop="";//last clicked operate
     
    private int btnWidth=49;//button's width
    private int btnHeight=26;//button's height
     
    public Calculate(){
         
        frm=new JFrame();
         
        frm.getContentPane().setLayout(null);//set layout for JTextField(Must)
        frm.setBounds(400, 300, 245, 235);//set x,y,width,height for JTextField
        frm.setTitle("Calculate");//set From title text
        frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frm.setResizable(false);
         
        txt=new JTextField();
        txt.setBounds(3, 5, 232, 28);//set x,y,width,height for JTextField
        txt.setBackground(Color.WHITE);//set background color for JTextField
        txt.setFont(new Font("",Font.PLAIN,19));//set font for JTextField
        txt.setEditable(false);
         
        //new JButton and add listener
        btn1=new JButton("1");btn1.addActionListener(new Listener());
        btn2=new JButton("2");btn2.addActionListener(new Listener());
        btn3=new JButton("3");btn3.addActionListener(new Listener());
        btn4=new JButton("4");btn4.addActionListener(new Listener());
        btn5=new JButton("5");btn5.addActionListener(new Listener());
        btn6=new JButton("6");btn6.addActionListener(new Listener());
        btn7=new JButton("7");btn7.addActionListener(new Listener());
        btn8=new JButton("8");btn8.addActionListener(new Listener());
        btn9=new JButton("9");btn9.addActionListener(new Listener());
        btn0=new JButton("0");btn0.addActionListener(new Listener());
        btnDot=new JButton(".");btnDot.addActionListener(new Listener());btnDot.setToolTipText("dot");
        btnAdd=new JButton("+");btnAdd.addActionListener(new Listener());
        btnCut=new JButton("-");btnCut.addActionListener(new Listener());
        btnMulti=new JButton("*");btnMulti.addActionListener(new Listener());
        btnDiv=new JButton("/");btnDiv.addActionListener(new Listener());
        btnEq=new JButton("=");btnEq.addActionListener(new Listener());
        btnC=new JButton("C");btnC.addActionListener(new Listener());btnC.setToolTipText("clear all");
        btnCE=new JButton("CE");btnCE.addActionListener(new Listener());btnCE.setToolTipText("clear current number");
        btnBsp=new JButton("<-");btnBsp.addActionListener(new Listener());btnBsp.setToolTipText("delete the number you just input");
        btnRemdr=new JButton("%");btnRemdr.addActionListener(new Listener());btnRemdr.setToolTipText("mod");
         
        //set JButton x,y,width,height
        btnBsp.setBounds(5, 40, btnWidth, btnHeight);
        btnCE.setBounds(65, 40, btnWidth, btnHeight);
        btnC.setBounds(125, 40, btnWidth, btnHeight);
        btnRemdr.setBounds(185, 40, btnWidth, btnHeight);
 
        btn1.setBounds(5, 70, btnWidth, btnHeight);
        btn2.setBounds(65, 70, btnWidth, btnHeight);
        btn3.setBounds(125, 70, btnWidth, btnHeight);
        btnAdd.setBounds(185, 70, btnWidth, btnHeight);
         
        btn4.setBounds(5, 100, btnWidth, btnHeight);
        btn5.setBounds(65, 100, btnWidth, btnHeight);
        btn6.setBounds(125, 100, btnWidth, btnHeight);
        btnCut.setBounds(185, 100, btnWidth, btnHeight);
         
        btn7.setBounds(5, 130, btnWidth, btnHeight);
        btn8.setBounds(65, 130, btnWidth, btnHeight);
        btn9.setBounds(125, 130, btnWidth, btnHeight);
        btnDiv.setBounds(185, 130, btnWidth, btnHeight);
         
        btnMulti.setBounds(5, 160, btnWidth, btnHeight);
        btn0.setBounds(65, 160, btnWidth, btnHeight);
        btnDot.setBounds(125, 160, btnWidth, btnHeight);
        btnEq.setBounds(185, 160, btnWidth, btnHeight);
         
        //add Companet to JFrame
        frm.add(txt);
        frm.add(btn1);
        frm.add(btn2);
        frm.add(btn3);
        frm.add(btn4);
        frm.add(btn5);
        frm.add(btn6);
        frm.add(btn7);
        frm.add(btn8);
        frm.add(btn9);
        frm.add(btn0);
        frm.add(btnDot);
        frm.add(btnAdd);
        frm.add(btnCut);
        frm.add(btnMulti);
        frm.add(btnDiv);
        frm.add(btnEq);
        frm.add(btnC);
        frm.add(btnCE);
        frm.add(btnBsp);
        frm.add(btnRemdr);
         
        //show JFrame
        frm.setVisible(true);
    }
     
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Calculate ta=new Calculate();
    }
}