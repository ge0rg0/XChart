/**
 * Copyright (c) 2011-2013 Xeiam LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.xchart.demo;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.xeiam.xchart.XChartPanel;
import com.xeiam.xchart.demo.charts.Example10;
import com.xeiam.xchart.demo.charts.Example11;
import com.xeiam.xchart.demo.charts.Example2;
import com.xeiam.xchart.demo.charts.Example3;
import com.xeiam.xchart.demo.charts.Example5;
import com.xeiam.xchart.demo.charts.Example6;
import com.xeiam.xchart.demo.charts.Example7;
import com.xeiam.xchart.demo.charts.Example8;
import com.xeiam.xchart.demo.charts.Example9;

/**
 * Class containing all XChart example charts
 * 
 * @author timmolter
 */
public class ChartDemo extends JPanel implements TreeSelectionListener {

  /** The main split frame */
  private JSplitPane splitPane;

  /** The tree */
  private JTree tree;

  /** The panel for chart */
  private JPanel chartPanel;

  /**
   * Constructor
   */
  public ChartDemo() {

    super(new GridLayout(1, 0));

    // Create the nodes.
    DefaultMutableTreeNode top = new DefaultMutableTreeNode("XChart Example Charts");
    createNodes(top);

    // Create a tree that allows one selection at a time.
    tree = new JTree(top);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    // Listen for when the selection changes.
    tree.addTreeSelectionListener(this);

    // Create the scroll pane and add the tree to it.
    JScrollPane treeView = new JScrollPane(tree);

    // Create Chart Panel
    chartPanel = new XChartPanel(new Example10().getChart());

    // Add the scroll panes to a split pane.
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitPane.setTopComponent(treeView);
    splitPane.setBottomComponent(chartPanel);

    Dimension minimumSize = new Dimension(100, 125);
    treeView.setMinimumSize(minimumSize);
    splitPane.setDividerLocation(100);
    splitPane.setPreferredSize(new Dimension(800, 800));

    // Add the split pane to this panel.
    add(splitPane);
  }

  @Override
  public void valueChanged(TreeSelectionEvent e) {

    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

    if (node == null) {
      return;
    }

    Object nodeInfo = node.getUserObject();
    // tree leaf
    if (node.isLeaf()) {
      ChartInfo chartInfo = (ChartInfo) nodeInfo;
      // displayURL(chartInfo.bookURL);
      chartPanel = new XChartPanel(chartInfo.getExampleChart());
      splitPane.setBottomComponent(chartPanel);
    }
    // tree branch node
    else {
      // do nothing
    }
  }

  private void createNodes(DefaultMutableTreeNode top) {

    // categories
    DefaultMutableTreeNode category = null;
    // leaves
    DefaultMutableTreeNode chart = null;

    // First category
    category = new DefaultMutableTreeNode("Line Charts");
    top.add(category);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example10", new Example10().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example11", new Example11().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example2", new Example2().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example3", new Example3().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example5", new Example5().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example6", new Example6().getChart()));
    category.add(chart);

    // Second category
    category = new DefaultMutableTreeNode("Other Charts");
    top.add(category);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example7", new Example7().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example8", new Example8().getChart()));
    category.add(chart);

    chart = new DefaultMutableTreeNode(new ChartInfo("Example9", new Example9().getChart()));
    category.add(chart);

  }

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked from the event dispatch thread.
   */
  private static void createAndShowGUI() {

    // Create and set up the window.
    JFrame frame = new JFrame("XChart Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add content to the window.
    frame.add(new ChartDemo());

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {

    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {

        createAndShowGUI();
      }
    });
  }
}