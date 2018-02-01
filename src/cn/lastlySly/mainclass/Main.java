package cn.lastlySly.mainclass;
import cn.lastlySly.calendarpanel.Calendarpanel;
import cn.lastlySly.note.note;

import java.io.File;

import java.awt.HeadlessException;

import javax.swing.*;

/**
 * 主程序，总面板
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2016-05-31 21:54
 **/
public class Main extends JFrame{
    private final JSplitPane jsplitpane=new JSplitPane();


    public Main() throws HeadlessException {
        super();
        // TODO Auto-generated constructor stub
        new Thread(new note()).start();
        this.setTitle("日历记事本");
        this.setSize(1050,400);
        this.setLocation(200, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jsplitpane.setDividerSize(10);
        jsplitpane.setDividerLocation(500);
        this.add(jsplitpane);
        jsplitpane.setLeftComponent(new Calendarpanel());
        jsplitpane.setRightComponent(new note());

        JOptionPane.showMessageDialog(null, "点击下方按钮进入程序，进入程序后点击日期可进入当日日志编辑，支持快捷键复制粘帖全选");

        File folder=new File("D:"+File.separator+"CalendarNotepadDataSpace");
        folder.mkdirs();
    }


    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}