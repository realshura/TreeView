package com.feeling.demos;

import android.view.View;
import android.widget.TextView;

import com.feeling.treeview.TreeNode;
import com.feeling.treeview.base.CheckableNodeViewBinder;

public class FileNodeViewBinder extends CheckableNodeViewBinder {
    TextView mSpaceView;
    TextView textView;

    public FileNodeViewBinder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.node_name_view);
        mSpaceView = (TextView) itemView.findViewById(R.id.spaceView);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tree_view_item_file;
    }

    @Override
    public void bindView(TreeNode treeNode) {
        mSpaceView.setWidth(dip2px(itemView.getContext(), 15 * (treeNode.getDepth() - 1)));
        textView.setText(treeNode.getValue().toString());
    }
}
