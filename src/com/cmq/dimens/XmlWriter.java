package com.cmq.dimens;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlRecursiveElementVisitor;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by caomengqi on 2017/2/24.
 */
public class XmlWriter extends  WriteCommandAction.Simple {
    private PsiElement mFile;
    float mValue;
    Dimens.MODIFY_TYPE mType;

    protected XmlWriter(Project project, PsiFile... files) {
        super(project, files);
        mFile = files.length > 0? files[0] : null;
    }

    public XmlWriter(Project project, float value, Dimens.MODIFY_TYPE type, PsiFile... files) {
        super(project, files);
        mFile = files.length > 0? files[0] : null;
        mValue = value;
        mType = type;
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
                            String pattern = "(\\D*)(\\d+)(.*)";

                            // 创建 Pattern 对象
                            Pattern r = Pattern.compile(pattern);

                            // 现在创建 matcher 对象
                            Matcher m = r.matcher(tag.getValue().getText());
                            if(m.find()) {
                                int value = Integer.valueOf(m.group(2));
                                if (mType == Dimens.MODIFY_TYPE.TYPE_PLUS) {
                                    value += mValue;
                                } else if (mType == Dimens.MODIFY_TYPE.TYPE_MINUS) {
                                    value -= mValue;
                                } else if (mType == Dimens.MODIFY_TYPE.TYPE_MULTI) {
                                    value *= mValue;
                                } else if (mType == Dimens.MODIFY_TYPE.TYPE_DIVIDE) {
                                    value /= mValue;
                                }
                                ((XmlTag) element).getValue().setText(value + m.group(3));
                            }
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
