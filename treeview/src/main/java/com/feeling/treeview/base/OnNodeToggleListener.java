package com.feeling.treeview.base;

import com.feeling.treeview.TreeNode;

public interface OnNodeToggleListener {
    boolean onNodeToggled(final TreeNode treeNode, boolean expand);
}
