/*
RemoveNodeAction.java
:tabSize=4:indentSize=4:noTabs=true:
:folding=explicit:collapseFolds=1:

Copyright (C) 2005 Ian Lewis (IanLewis@member.fsf.org)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
Optionally, you may find a copy of the GNU General Public License
from http://www.fsf.org/copyleft/gpl.txt
*/

package treeview.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import org.w3c.dom.DOMException;

import treeview.*;
import net.sourceforge.jsxe.jsXe;
import net.sourceforge.jsxe.gui.DocumentView;
import net.sourceforge.jsxe.gui.Messages;
import net.sourceforge.jsxe.dom.AdapterNode;

/**
 * An action that removes the current selected node from the tree.
 *
 * @author Ian Lewis (<a href="mailto:IanLewis@member.fsf.org">IanLewis@member.fsf.org</a>)
 * @version $Id: RemoveNodeAction.java,v 1.1 2005/04/28 22:18:40 ian_lewis Exp $
 */
public class RemoveNodeAction extends AbstractAction {
    
    //{{{ RemoveNodeAction constructor
    
    public RemoveNodeAction() {
        putValue(Action.NAME, Messages.getMessage("TreeView.RemoveNode"));
    }//}}}
  
    //{{{ actionPerformed()
  
    public void actionPerformed(ActionEvent e) {
         DocumentView view = jsXe.getActiveView().getDocumentView();
        if (view instanceof DefaultView) {
            
            DefaultView defView = (DefaultView)view;
            DefaultViewTree tree = defView.getDefaultViewTree();
            AdapterNode selectedNode = tree.getSelectedNode();
            if (selectedNode != null) {
                try {
                    selectedNode.getParentNode().remove(selectedNode);
                    //The TreeModel doesn't automatically treeNodesRemoved() yet
                    tree.updateUI();
                } catch (DOMException dome) {
                    JOptionPane.showMessageDialog(tree, dome, "XML Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//}}}
  
}
