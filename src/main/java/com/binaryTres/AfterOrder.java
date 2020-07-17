package com.binaryTres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 前序 + 中序 -> 重塑二叉树
 */
public class PreOrder {
    public static void main(String[] args) {
        List<Integer> preOrder = Arrays.asList(1, 2, 4, 7, 3, 5, 6, 8);
        List<Integer> midOrder = Arrays.asList(4, 7, 2, 1, 5, 3, 8, 6);

        if (CollectionUtils.isEmpty(preOrder) || CollectionUtils.isEmpty(midOrder) || preOrder.size() != midOrder
                .size()) {
            // 报参数错误
        }
        BTreeNode bTreeNode = build(preOrder, midOrder);
        System.out.println("ghjk");
    }

    public static BTreeNode build(List<Integer> preOrder, List<Integer> midOrder) {
        Integer rootData = preOrder.get(0);
        BTreeNode rootBTreeNode = new BTreeNode(rootData);
        int length = preOrder.size();
        if (length == 1) {
            rootBTreeNode.setLeftNode(null);
            rootBTreeNode.setRightNode(null);
            return rootBTreeNode;
        }
        int flag = 0;
        for (int i = 0; i < length; i++) {
            if (midOrder.get(i) == rootData) {
                flag = i;
                break;
            }
        }
        // 构造根节点左子树
        if (flag > 0) {
            List<Integer> leftPreOrder = new ArrayList<>();
            List<Integer> leftMidOrder = new ArrayList<>();
            // j是中序的下标
            for (int j = 0; j < flag; j++) {
                // 构造根节点左子树的先序
                leftPreOrder.add(preOrder.get(j + 1));
                // 构造根节点左子树的中序
                leftMidOrder.add(midOrder.get(j));
            }
            rootBTreeNode.leftNode = build(leftPreOrder, leftMidOrder);
        } else {
            rootBTreeNode.leftNode = null;
        }
        //
        if (length - flag - 1 > 0) {
            List<Integer> rightPreOrder = new ArrayList<>();
            List<Integer> rightMidOrder = new ArrayList<>();
            // j是中序的下标
            for (int j = flag + 1; j < length; j++) {
                rightPreOrder.add(preOrder.get(j));
                rightMidOrder.add(midOrder.get(j));
            }
            rootBTreeNode.rightNode = build(rightPreOrder, rightMidOrder);

        } else {
            rootBTreeNode.rightNode = null;
        }
        return rootBTreeNode;
    }

    static class BTreeNode {
        Integer data;
        BTreeNode leftNode;
        BTreeNode rightNode;

        public BTreeNode(Integer integer) {
            this.data = integer;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public BTreeNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(BTreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public BTreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(BTreeNode rightNode) {
            this.rightNode = rightNode;
        }
    }
}
