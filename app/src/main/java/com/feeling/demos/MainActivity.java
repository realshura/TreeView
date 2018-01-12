package com.feeling.demos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.feeling.treeview.TreeNode;
import com.feeling.treeview.TreeView;
import com.feeling.treeview.base.BaseNodeViewBinder;
import com.feeling.treeview.base.BaseNodeViewFactory;
import com.feeling.treeview.base.OnNodeToggleListener;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mContainer;
    private TreeView mTreeView;
    private TreeNode mRootTreeNode;

    private TreeNode mCurrTreeNode;

    private ProgressDialog mLoadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadDialog = new ProgressDialog(this);
        initTreeView();

        buildTree();
    }


    private void initTreeView() {
        mContainer = (ViewGroup) findViewById(R.id.container);
        mRootTreeNode = TreeNode.root();
        mTreeView = new TreeView(mRootTreeNode, this, new BaseNodeViewFactory() {
            @Override
            public BaseNodeViewBinder getNodeViewBinder(View view, int viewType) {
                switch (viewType) {
                    case 0:
                        return new FolderNodeViewBinder(view);
                    case 1:
                        return new FileNodeViewBinder(view);
                    default:
                        return new FileNodeViewBinder(view);
                }
            }
        });
        View view = mTreeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContainer.addView(view);
        mTreeView.setTreeToggleListener(new OnNodeToggleListener() {
            @Override
            public boolean onNodeToggled(final TreeNode treeNode, boolean expand) {

                if (treeNode.getLevel() == 1) {
                    return false;
                }

                if (expand && (treeNode.getChildren() == null || treeNode.getChildren().size() == 0)) {
                    getChildNode(treeNode);
                    return false;
                }
                return true;
            }
        });
    }


    private void getChildNode(final TreeNode treeNode) {
        mCurrTreeNode = treeNode;
        mLoadDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadDialog.dismiss();

                treeNode.addChild(new TreeNode(2, 2, "运营管理部"));
                treeNode.addChild(new TreeNode(2, 2, "放款组"));
                treeNode.addChild(new TreeNode(2, 2, "运营管组"));
                mTreeView.expandNode(treeNode);
                mTreeView.refreshTreeView();
            }
        }, 500);
    }

    private void buildTree() {

        // 0
        TreeNode treeNode = new TreeNode(0, 0, "阿里巴巴");

        // 1
        treeNode.addChild(new TreeNode(1, 1, "代理商"));

        // 1
        TreeNode tempNode = new TreeNode(0, 1, "运营管理部");
        treeNode.addChild(tempNode);
//        tempNode.addChild(new TreeNode(2, 2, "运营管理部"));
//        tempNode.addChild(new TreeNode(2, 2, "放款组"));
//        tempNode.addChild(new TreeNode(2, 2, "运营管组"));

        // 1
        treeNode.addChild(new TreeNode(1, 1, "对公业务部"));

        // 1
        tempNode = new TreeNode(0, 1, "企业营销部");
        treeNode.addChild(tempNode);
        tempNode.addChild(new TreeNode(1, 2, "产品组"));
        tempNode.addChild(new TreeNode(1, 2, "企划营销部"));
        tempNode.addChild(new TreeNode(1, 2, "渠道组"));
        tempNode.addChild(new TreeNode(1, 2, "培训组"));
        tempNode.addChild(new TreeNode(1, 2, "市场组"));
        tempNode.addChild(new TreeNode(1, 2, "企划组"));

        // 1
        tempNode = new TreeNode(0, 1, "综合管理部");
        treeNode.addChild(tempNode);

        TreeNode aa = new TreeNode(0, 2, "人事组");
        aa.addChild(new TreeNode(1, 3, "袁腾飞"));
        aa.addChild(new TreeNode(1, 3, "高晓松"));

        tempNode.addChild(aa);
        tempNode.addChild(new TreeNode(1, 2, "IT组"));
        tempNode.addChild(new TreeNode(1, 2, "行政组"));

        // 1
        treeNode.addChild(new TreeNode(1, 1, "资产管理部"));


        // 1
        tempNode = new TreeNode(0, 1, "风险控制部");
        treeNode.addChild(tempNode);
        tempNode.addChild(new TreeNode(1, 2, "风险控制部"));
        tempNode.addChild(new TreeNode(1, 2, "风险管理组"));
        tempNode.addChild(new TreeNode(1, 2, "风控组"));

        // 1
        tempNode = new TreeNode(0, 1, "财务部");
        treeNode.addChild(tempNode);
        tempNode.addChild(new TreeNode(1, 2, "财务核算中"));
        tempNode.addChild(new TreeNode(1, 2, "财务管理组"));
        tempNode.addChild(new TreeNode(1, 2, "财务部"));

        // 1
        treeNode.addChild(new TreeNode(1, 1, "总经理室"));

        // 1
        treeNode.addChild(new TreeNode(1, 1, "分管领导"));

        // 1
        treeNode.addChild(new TreeNode(1, 1, "商用车业务部"));

        mRootTreeNode.addChild(treeNode);
        mTreeView.refreshTreeView();
    }
}
