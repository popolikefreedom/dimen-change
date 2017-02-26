package com.cmq.dimens;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;


/**
 * Created by caomengqi on 2017/2/24.
 */
public class DimensChangeAction extends AnAction{
    protected Dimens mDialog;

    public DimensChangeAction() {
        super("dimensChange");
    }


    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiFile file = PsiUtilBase.getPsiFileInEditor(editor, project);
//        XmlWriter writer = new XmlWriter(project, file);
//        writer.execute();
        //getIDsFromLayout(file);
        System.out.println("actionPerformed");
        showDialog(project, file);
    }


    private void showDialog(Project project, PsiFile file){
        mDialog = new Dimens(project, file);
        mDialog.setSize(600, 100);
        mDialog.setLocationRelativeTo(null);
        mDialog.setVisible(true);
    }

    protected void closeDialog() {
        if (mDialog == null) {
            return;
        }

        mDialog.setVisible(false);
        mDialog.dispose();
    }
}
