package com.cmq.dimens;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlRecursiveElementVisitor;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

/**
 * Created by caomengqi on 2017/2/24.
 */
public class XmlWriter extends  WriteCommandAction.Simple {
    private PsiElement mFile;

    protected XmlWriter(Project project, PsiFile... files) {
        super(project, files);
        mFile = files.length > 0? files[0] : null;
    }

    @Override
    protected void run() throws Throwable {
        if (mFile != null) {
            mFile.accept(new XmlRecursiveElementVisitor() {

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
    }
}
