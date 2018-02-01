package cn.lastlySly.calendarpanel;
import cn.lastlySly.calendardeal.Calendardata;
import cn.lastlySly.note.note;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 日历面板
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2016-05-31 21:52
 **/
public class Calendarpanel extends JPanel implements ActionListener{
    static public JLabel showMessage=new JLabel("",JLabel.CENTER);

    JLabel labelday[]=new JLabel[42];

    JLabel titlename[]=new JLabel[7];
    String name[]={"周日","周一","周二","周三","周四","周五","周六"};
    JTextField text1,text2;
    JButton nextmonth,previousmonth,Enter;
    JLabel lab1,lab2,lab3;
    int year,month;
    Calendardata calendar;
    String Day="1";
    /*JLabel showtip=new JLabel("点击日期进入当日日记编辑");*/
    note nn=new note();

    public Calendarpanel() {
        // TODO Auto-generated constructor stub
        JPanel pcenter=new JPanel();
        pcenter.setLayout(new GridLayout(7,7));/*网格布局*/
		/*设置星期标签*/
        for(int i=0;i<7;i++){
            titlename[i]=new JLabel(name[i],JLabel.CENTER);
            pcenter.add(titlename[i]);
            titlename[i].setBorder(BorderFactory.createLineBorder(Color.black));/*设置边界线color*/
            titlename[i].setFont(new Font("TimesRoman", Font.BOLD, 16));
            if(i==0){
                titlename[i].setForeground(Color.RED);/*设置周六周日font为红色*/
            }
            if(i==6){
                titlename[i].setForeground(Color.BLUE);
            }
        }
		/*设置日期标签*/
        for(int i=0;i<42;i++){
            labelday[i]=new JLabel("",JLabel.CENTER);
            labelday[i].setBorder(BorderFactory.createLineBorder(Color.blue));
            labelday[i].setForeground(Color.darkGray);
            pcenter.add(labelday[i]);
            if(i%7==6){
                labelday[i].setForeground(Color.blue);
            }
            if(i%7==0){
                labelday[i].setForeground(Color.red);
            }
            final int j=i;
			/*添加鼠标监听器内部类,适配器*/
            labelday[j].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO 自动生成的方法存根
					/*处理鼠标释放*/
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO 自动生成的方法存根

					/*处理鼠标按下*/
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO 自动生成的方法存根
					/*处理鼠标离开*/
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO 自动生成的方法存根
					/*处理鼠标移入*/

                }

                @Override
                public void mouseClicked(MouseEvent e){
                    // TODO 自动生成的方法存根
					/*处理鼠标点击*/
					/*获取日期   天*/

                    Day=labelday[j].getText();
                    try {
                        if(!Day.equals("")){
							/*设置选中日期标签fontcolor*/

							/*初始化上次点击的标签*/
                            for(int i=0;i<42;i++){
                                if(i%7==6){
                                    labelday[i].setForeground(Color.blue);
                                    labelday[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                                }
                                if(i%7==0){
                                    labelday[i].setForeground(Color.red);
                                    labelday[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                                }
                                if(!(i%7==6) && !(i%7==0)){
                                    labelday[i].setForeground(Color.darkGray);
                                    labelday[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                                }
                            }

                            labelday[j].setForeground(Color.green);
                            labelday[j].setFont(new Font("TimesRoman", Font.BOLD, 16));
                            showMessage.setText(calendar.getYear()+"年"+calendar.getMonth()+"月"+Day+"日");
                            text1.setText(""+calendar.getYear());
                            text2.setText(""+calendar.getMonth());
                            File file=new File("D:"+File.separator+"CalendarNotepadDataSpace"+File.separator+showMessage.getText()+".txt");

                            if(file.exists()){
                                String h=showMessage.getText();
                                String m=h+"有日志记录，是否查看？";
                                int ok=JOptionPane.showConfirmDialog(getParent(),m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                                if(ok==JOptionPane.YES_OPTION){
                                    try {
                                        FileReader in=new FileReader(file);
                                        BufferedReader bufr=new BufferedReader(in);
                                        char a[]=new char[1024];
                                        int len=bufr.read(a);
                                        note.area.setText(new String(a,0,len));

										/*	System.out.println(new String(a,0,len));*/

                                        bufr.close();
                                        in.close();

                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(getParent(), showMessage.getText()+"没有日志记录，清编辑并保存");
                                note.area.setText("");
                            }
                        }
                    } catch (NullPointerException e2) {
                        // TODO: handle exception
							/*JOptionPane.showMessageDialog(null, "此处为空日期");*/
                    }
                }
            });
        }



		/*初始化日期*/
        calendar=new Calendardata();
        year=calendar.getYear();
        month=calendar.getMonth();
        calendar.setYear(year);
        calendar.setMonth(month);
        String day[]=calendar.getCalendar();

		/*设置日期标签名*/
        for(int i=0;i<42;i++){
            labelday[i].setText(day[i]);
        }
		/*组件添加*/
        lab1=new JLabel("请输入日期");
        lab2=new JLabel("年份");
        lab3=new JLabel("月份");
        Enter=new JButton("搜索");
        text1=new JTextField(5);
        text2=new JTextField(5);
        nextmonth=new JButton("下月");
        previousmonth=new JButton("上月");
        Enter.addActionListener(this);
        nextmonth.addActionListener(this);
        previousmonth.addActionListener(this);

        JPanel jpnorth=new JPanel(),jpsouth=new JPanel();
        jpnorth.add(lab1);
        jpnorth.add(lab2);
        jpnorth.add(text1);
        jpnorth.add(lab3);
        jpnorth.add(text2);
        jpnorth.add(Enter);
        jpnorth.add(previousmonth);
        jpnorth.add(nextmonth);
        jpsouth.add(note.tim);

		/*左侧面包下端提示*/
		/*showtip.setForeground(Color.orange);
		showtip.setFont(new Font("TimesRoman", Font.BOLD, 17));;*/
		/*右侧面板日期标签*/
        showMessage.setText(calendar.getYear()+"年"+calendar.getMonth()+"月"+calendar.getDay()+"日");
        showMessage.setForeground(Color.PINK);
        showMessage.setFont(new Font("TimesRoman", Font.BOLD, 18));;

        ScrollPane scarollpane=new ScrollPane();
        scarollpane.add(pcenter);
        this.setLayout(new BorderLayout());
        this.add(scarollpane,BorderLayout.CENTER);
        this.add(jpnorth, BorderLayout.NORTH);
        this.add(jpsouth,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==nextmonth){

            if(month>=12){
                month=1;
                year=year+1;
                calendar.setMonth(month);
                calendar.setYear(year);
                String day[]=calendar.getCalendar();
                for(int i=0;i<42;i++){
                    labelday[i].setText(day[i]);
                }
            }
            else{
                month=month+1;
                calendar.setMonth(month);
                calendar.setYear(year);
                String day[]=calendar.getCalendar();
                for(int i=0;i<42;i++){
                    labelday[i].setText(day[i]);
                }
            }
            showMessage.setText(calendar.getYear()+"年"+calendar.getMonth()+"月"+Day+"日");
            text1.setText(""+calendar.getYear());
            text2.setText(""+calendar.getMonth());
			/*nn.setshowtime(year,month);*/


        }
        else if(e.getSource()==previousmonth){
            if(month<=1){
                month=12;
                year=year-1;
                calendar.setYear(year);
                calendar.setMonth(month);
                String day[]=calendar.getCalendar();
                for(int i=0;i<42;i++){
                    labelday[i].setText(day[i]);
                }
            }
            else{
                month=month-1;
                calendar.setMonth(month);
                calendar.setYear(year);
                String day[]=calendar.getCalendar();
                for(int i=0;i<42;i++){
                    labelday[i].setText(day[i]);
                }
            }
            showMessage.setText(calendar.getYear()+"年"+calendar.getMonth()+"月"+Day+"日");
				/*nn.setshowtime(year,month);*/
            text1.setText(""+calendar.getYear());
            text2.setText(""+calendar.getMonth());



        }
        else{
            String yea=text1.getText();
            String mon=text2.getText();
            try{
                year=Integer.parseInt(yea);
                month=Integer.parseInt(mon);
                if(month>12 || month<1 || year<1){
                    JOptionPane.showMessageDialog(null, "请输入正确年份或月份");
                    return;
                }else{
                    calendar.setYear(year);
                    calendar.setMonth(month);
                }
                calendar.setMonth(month);
                String day[]=calendar.getCalendar();
                for(int i=0;i<42;i++){
                    labelday[i].setText(day[i]);
                }

            }catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "请输入正确年份或月份");
            }
            showMessage.setText(calendar.getYear()+"年"+calendar.getMonth()+"月"+Day+"日");
				/*nn.setshowtime(year,month);*/
            text1.setText(""+calendar.getYear());
            text2.setText(""+calendar.getMonth());

        }
    }
}
