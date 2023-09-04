package reservation;

public class tab_menu {

}
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

package reservation;

public class tab_menu {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());

        TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

        TabItem tab1 = new TabItem(tabFolder, SWT.NONE);
        tab1.setText("Tab 1");
        Text text1 = new Text(tabFolder, SWT.BORDER | SWT.MULTI);
        tab1.setControl(text1);

        TabItem tab2 = new TabItem(tabFolder, SWT.NONE);
        tab2.setText("Tab 2");
        Text text2 = new Text(tabFolder, SWT.BORDER | SWT.MULTI);
        tab2.setControl(text2);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
