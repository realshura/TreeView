package com.feeling.demos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feeling.treeview.TreeNode;
import com.feeling.treeview.base.CheckableNodeViewBinder;

public class FolderNodeViewBinder extends CheckableNodeViewBinder {

    TextView mSpaceView;
    TextView textView;
    ImageView imageView;

    public FolderNodeViewBinder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.node_name_view);
        imageView = (ImageView) itemView.findViewById(R.id.arrow_img);
        mSpaceView = (TextView) itemView.findViewById(R.id.spaceView);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tree_view_item_folder;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
        textView.setText(treeNode.getValue().toString());
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
        mSpaceView.setWidth(dip2px(itemView.getContext(), 15 * treeNode.getDepth()));
    }

    @Override
    public void onNodeToggled(final TreeNode treeNode, boolean expand) {
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();
        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }
}
