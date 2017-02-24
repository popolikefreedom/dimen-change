package com.cmq.dimens;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlRecursiveElementVisitor;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

/**
 * Created by caomengqi on 2017/2/24.
 */
public class DimensChangeAction extends AnAction {
    public DimensChangeAction() {
        super("dimensChange");
    }


    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiFile file = PsiUtilBase.getPsiFileInEditor(editor, project);
        XmlWriter writer = new XmlWriter(project, file);
        writer.execute();
        //getIDsFromLayout(file);
    }

    /**
     * Obtain all IDs from layout
     *
     * @param file
     * @return
     */
    public static void getIDsFromLayout(final PsiFile file) {
        file.accept(new XmlRecursiveElementVisitor() {

            @Override
            public void visitElement(final PsiElement element) {
                super.visitElement(element);
                if (element instanceof XmlTag) {
                    XmlTag tag = (XmlTag) element;
                    if (tag.getName().equalsIgnoreCase("dimen")) {
                        ((XmlTag) element).getValue().setText("32dp");
                        XmlAttribute name = tag.getAttribute("name", null);
                        System.out.println("dimen : " + name.getValue() + "," + tag.getValue().getText());
//                        tag.getValue().setText("32dp");
                    }
                }
            }
        });


    }

//    private static Document a(AnActionEvent var0) {
//        Editor var1 = (Editor)var0.getData(CommonDataKeys.EDITOR);
//        return var1 != null?var1.getDocument():null;
//    }
}
