package com.cmq.dimens;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by caomengqi on 2017/2/24.
 */
public class EditDialog extends JFrame {
    private JPanel contentPane;
    private JButton okButton;
    private JButton cancelButton;
    private JTextPane editTP;
    private JButton resetButton;
    private JLabel titleLB;
    private String titleName;
    private String editStr;
    private Type type;

    public EditDialog(Type type) {
        this.type = type;
        setContentPane(contentPane);
//        setModal(true);
        this.setAlwaysOnTop(true);
        getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setTitle("Convert method");

        switch (type) {
            case OBJECT_FROM_DATA:
                titleName = "objectFromData(Object data)";
                break;
            case OBJECT_FROM_DATA1:
                titleName = "objectFromData(Object data,String key)";
                break;
            case ARRAY_FROM_DATA:
                titleName = "arrayFromData(Object data)";
                break;
            case ARRAY_FROM_DATA1:
                titleName = "arrayFromData(Object data,String key)";
                break;
        }
        titleLB.setText(titleName);
        editTP.setText(editStr);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resetAction();
            }
        });


    }

    private void resetAction() {

        switch (type) {
            case OBJECT_FROM_DATA:
                break;
            case OBJECT_FROM_DATA1:
                break;
            case ARRAY_FROM_DATA:
                break;
            case ARRAY_FROM_DATA1:
                break;
        }
    }

    private void onOK() {

        switch (type) {
            case OBJECT_FROM_DATA:
                break;
            case OBJECT_FROM_DATA1:
                break;
            case ARRAY_FROM_DATA:
                break;
            case ARRAY_FROM_DATA1:
                break;
        }

        dispose();
    }

    private void onCancel() {

        dispose();
    }

    public enum Type {
        OBJECT_FROM_DATA, OBJECT_FROM_DATA1, ARRAY_FROM_DATA, ARRAY_FROM_DATA1;
    }
}
