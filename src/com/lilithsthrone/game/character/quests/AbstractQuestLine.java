package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.TreeNode;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @since 0.1.1
 * @version 0.4
 * @author Innoxia
 */
public class AbstractQuestLine {
	private boolean mod;
	private boolean fromExternalFile;
	private String author;
	private String name, completedDescription;
	private QuestType type;
	private TreeNode<AbstractQuest> questTree;
	private TreeNode<String> questTreeById;

	public AbstractQuestLine(String name, String completedDescription, QuestType type, TreeNode<AbstractQuest> questTree) {
		this.name = name;
		this.completedDescription = completedDescription;
		this.type = type;
		this.questTree = questTree;
	}

	public AbstractQuestLine(File XMLFile, String author, String id, boolean mod)
	{
		if (XMLFile.exists())
		{
			try {
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				this.author = author;

				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.completedDescription = "";
				if (coreElement.getOptionalFirstOf("completedDescription").isPresent())
					this.completedDescription = coreElement.getMandatoryFirstOf("completedDescription").getTextContent();
				this.type = QuestType.valueOf(coreElement.getMandatoryFirstOf("type").getTextContent());

				var questNodesMap = new HashMap<String, QuestNode>();
				QuestNode rootNode = null;

				for (var questNode : coreElement.getMandatoryFirstOf("questTree").getAllOf("quest"))
				{
					var questId = questNode.getAttribute("id");
					var root = Boolean.valueOf(questNode.getAttribute("root"));
					var childrenId = new ArrayList<String>();
					for (var child : questNode.getAllOf("child"))
					{
						childrenId.add(child.getTextContent());
					}
					var questTreeNode = new QuestNode(questId, childrenId);
					questNodesMap.put(questId, questTreeNode)
					// if there are multiple roots, the last node with root will be the root
					if (root) rootNode = questTreeNode;
				}

				if (rootNode == null) throw new Exception("Quest tree must have a root node!");

				this.questTreeById = convertToStringTreeNode(rootNode, questNodesMap);

			} catch (Exception ex) {
				System.err.println("QuestLines was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
				ex.printStackTrace();
			}
		}
		System.err.println("QuestLines file does not exist! (" + XMLFile.getName() + ")");
	}

	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public String getCompletedDescription() {
		return completedDescription;
	}

	public QuestType getType() {
		return type;
	}

	public TreeNode<AbstractQuest> getQuestTree() {
		if (questTreeById != null) {
			questTree = questTreeById.convertTo(Quest::getQuestFromId);
			questTreeById = null;
		}
		return questTree;
	}

	private TreeNode<String> convertToStringTreeNode(QuestNode node, HashMap<String, QuestNode> map) {
		TreeNode<String> treeNode = new TreeNode<>(node.id);
		for (var childId : node.childrenId) {
			treeNode.addChild(convertToStringTreeNode(map.get(childId), map));
		}
		return treeNode;
	}


	private static class QuestNode {
		String id;
		List<String> childrenId;

		QuestNode(String id, List<String> childrenId) {
			this.id = id;
			this.childrenId = childrenId;
		}
	}
}
