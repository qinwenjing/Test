package com.lruTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    int capacity = 6;
    final String HEAD_NODE = "headNode";
    public Map<String, DLinkedNode> map = new LinkedHashMap<>(capacity);

    public void initCache() {
        DLinkedNode headNode = new DLinkedNode();
        headNode.setData(HEAD_NODE);
        headNode.setNextNode(headNode);
        headNode.setBeforeNode(headNode);
        map.put(HEAD_NODE, headNode);
    }

    // 在头元素后面放第一个实际元素
    public void putHeadNextNode(String key) {
        DLinkedNode headNode = map.get(HEAD_NODE);
        DLinkedNode nextHeadNode = headNode.getNextNode();

        DLinkedNode node = new DLinkedNode();
        node.setData(key);
        node.setBeforeNode(headNode);
        node.setNextNode(nextHeadNode);

        headNode.setNextNode(node);

        nextHeadNode.setBeforeNode(node);

        map.put(key, node);
    }

    public void putNode(String key) {
        // 本来就存在该元素
        if (map.get(key) != null) {
            deleteNode(key);
            putHeadNextNode(key);
        } else { // 不存在该元素
            if (map.entrySet().size() < capacity) {
                putHeadNextNode(key);
            } else { // 先移出一个元素，在放
                delelteLastNode();
                putHeadNextNode(key);
            }
        }
    }

    public void delelteLastNode() {
        DLinkedNode headNode = map.get(HEAD_NODE);
        DLinkedNode lastNode = headNode.getBeforeNode();
        if (lastNode == null) {
            return;
        }
        deleteNode(lastNode.getData());
    }

    public void deleteNode(String key) {
        if (map.get(key) != null) {
            DLinkedNode beforeNode = map.get(key).getBeforeNode();
            DLinkedNode nextNode = map.get(key).getNextNode();
            // 因为有设置的默认的头结点，所以这两个节点不会为空
            beforeNode.setNextNode(nextNode);
            nextNode.setBeforeNode(beforeNode);

            map.remove(key);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache();
        lruCache.initCache();
        lruCache.putNode("aaaa");
        lruCache.putNode("bbbb");
        lruCache.putNode("cccc");
        lruCache.putNode("dddd");
        lruCache.putNode("ffff");
        lruCache.putNode("gggg");

        System.out.println("bbbbb");

    }

    static class DLinkedNode {
        String data;
        DLinkedNode nextNode;
        DLinkedNode beforeNode;

        public DLinkedNode() {
            data = null;
            nextNode = null;
            beforeNode = null;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public DLinkedNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(DLinkedNode nextNode) {
            this.nextNode = nextNode;
        }

        public DLinkedNode getBeforeNode() {
            return beforeNode;
        }

        public void setBeforeNode(DLinkedNode beforeNode) {
            this.beforeNode = beforeNode;
        }

        @Override
        public String toString() {
            return "data=" + data;
        }
    }
}
