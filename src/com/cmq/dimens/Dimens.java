package com.cmq.dimens;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2017/2/25.
 */
public class Dimens extends JFrame{
    private JButton divide;
    private JPanel jPanel1;
    private JButton plus;
    private JButton minus;
    private JButton multi;
    private JTextField textField1;
    private JButton okButton;

    private MODIFY_TYPE type = MODIFY_TYPE.TYPE_PLUS;

    private Project mProject;
    private PsiFile mPsiFile;

    public Dimens(Project project, PsiFile file){
        init();
        mProject = project;
        mPsiFile = file;
    }

    private void init(){
        System.out.println("init");
        setContentPane(jPanel1);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ok");
                modifyDimens(type);
                setVisible(false);
                dispose();
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("plus");
                type = MODIFY_TYPE.TYPE_PLUS;
                textField1.setText("+");
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("minus");
                type = MODIFY_TYPE.TYPE_MINUS;
                textField1.setText("+");
            }
        });

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("multi");
                type = MODIFY_TYPE.TYPE_MULTI;
                textField1.setText("+");
            }
        });

        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("divide");
                type = MODIFY_TYPE.TYPE_DIVIDE;
                textField1.setText("+");
            }
        });
    }

    private void modifyDimens(MODIFY_TYPE type) {
        XmlWriter writer = new XmlWriter(mProject, 2, type, mPsiFile);
        writer.execute();
    }

    public enum MODIFY_TYPE{
        TYPE_PLUS,TYPE_MINUS,TYPE_MULTI,TYPE_DIVIDE;
    }
}
