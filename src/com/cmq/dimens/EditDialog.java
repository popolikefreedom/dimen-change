package com.cmq.dimens;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by caomengqi on 2017/2/24.
 */
public class EditDialog extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPane2;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel errorLB;
    private JTextPane editTP;
    private JButton settingButton;
    private JLabel generateClassLB;
    private JTextField generateClassTF;
    private JPanel generateClassP;
    private JButton formatBtn;
    private PsiClass cls;
    private PsiFile file;
    private Project project;
    private String errorInfo = null;
    private String currentClass = null;

    public EditDialog() throws HeadlessException {
        setContentPane(contentPane2);
        setTitle("GsonFormat");
        getRootPane().setDefaultButton(okButton);
        this.setAlwaysOnTop(true);
        initGeneratePanel(file);
        initListener();
    }

    private void initListener() {

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (generateClassTF.isFocusOwner()) {
                    editTP.requestFocus(true);
                } else {
                    onOK();
                }
            }
        });
        formatBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        editTP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    onOK();
                }
            }
        });
        generateClassP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    editTP.requestFocus(true);
                }
            }
        });
        errorLB.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (errorInfo != null) {
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        settingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSettingDialog();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane2.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initGeneratePanel(PsiFile file) {

        cardLayout = (CardLayout) generateClassP.getLayout();
        generateClassTF.setBackground(errorLB.getBackground());
        currentClass = ((PsiJavaFileImpl) file).getPackageName() + "." + file.getName().split("\\.")[0];
        generateClassLB.setText(currentClass);
        generateClassTF.setText(currentClass);
        generateClassTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                cardLayout.next(generateClassP);
                if (TextUtils.isEmpty(generateClassTF.getText())) {
                    generateClassLB.setText(currentClass);
                    generateClassTF.setText(currentClass);
                } else {
                    generateClassLB.setText(generateClassTF.getText());
                }
            }
        });

        generateClassLB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                cardLayout.next(generateClassP);
                generateClassTF.requestFocus(true);
            }

        });
    }

    private void onOK() {

        this.setAlwaysOnTop(false);
        String jsonSTR = editTP.getText().trim();
        if (TextUtils.isEmpty(jsonSTR)) {
            return;
        }
        String generateClassName = generateClassTF.getText().replaceAll(" ", "").replaceAll(".java$", "");
        if (TextUtils.isEmpty(generateClassName) || generateClassName.endsWith(".")) {
            return;
        }
        PsiClass generateClass = null;
        if (!currentClass.equals(generateClassName)) {
        } else {
            generateClass = cls;
        }

    }
    private void onCancel() {
        dispose();
    }


    public PsiClass getClss() {
        return cls;
    }

    public void setClass(PsiClass mClass) {
        this.cls = mClass;
    }

    public void setProject(Project mProject) {
        this.project = mProject;
    }

    public void setFile(PsiFile mFile) {
        this.file = mFile;
    }

    private void createUIComponents() {

    }

    public void openSettingDialog() {

    }


    public void cleanErrorInfo() {
        errorInfo = null;
    }

    public void setErrorInfo(String error) {
        errorInfo = error;
    }


}
