package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.dialogue.utils.UtilText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AbstractQuest {
    private final boolean mod;
    private final boolean fromExternalFile;
    private final String author;
    private String id;

    protected String name, description, completeDescription;
    protected String tooltipDescription;

    private final int level, experienceReward;
    private final QuestType questType;

    public AbstractQuest(QuestType questType, int level, int experienceReward) {
        this(questType, level, experienceReward, false, false, "");
    }

    public AbstractQuest(QuestType questType, int level, int experienceReward, boolean mod, boolean fromExternalFile, String author) {
        this.questType = questType;

        this.level = level;
        this.experienceReward = experienceReward;

        this.mod = mod;
        this.fromExternalFile = fromExternalFile;
        this.author = author;
    }


    public static List<AbstractQuest> loadQuestsFromFile(File XMLFile, String author, String idPrefix, boolean mod)
    {
        if (XMLFile.exists())
        {
            List<AbstractQuest> loadedQuests = new ArrayList<>();
            try {
                Element coreElement = Element.getDocumentRootElement(XMLFile);

                for (Element questElement : coreElement.getAllOf("quest")) {

                    String tagId = questElement.getAttribute("id");

                    String name = questElement.getMandatoryFirstOf("name").getTextContent();

                    QuestType questType = QuestType.valueOf(questElement.getMandatoryFirstOf("type").getTextContent());

                    String description = questElement.getMandatoryFirstOf("description").getTextContent();
                    String completeDescription = description;
                    if (questElement.getOptionalFirstOf("completeDescription").isPresent())
                    {
                        completeDescription = questElement.getMandatoryFirstOf("completeDescription").getTextContent();
                    }
                    int level = Integer.parseInt(questElement.getMandatoryFirstOf("level").getTextContent());
                    int experienceReward = Integer.parseInt(questElement.getMandatoryFirstOf("experienceReward").getTextContent());

                    AbstractQuest quest = new AbstractQuest(questType, level, experienceReward, mod, true, author) {
                        @Override
                        public String getName() {
                            return name;
                        }

                        @Override
                        public String getId() {
                            return idPrefix + "_" + tagId;
                        }

                        @Override
                        public String getDescription() {
                            return UtilText.parse(description);
                        }

                        @Override
                        public String getCompletedDescription() {
                            return UtilText.parse(completeDescription);
                        }
                    };

                    quest.name = name;
                    quest.description = description;
                    quest.completeDescription = completeDescription;

                    loadedQuests.add(quest);
                }

                return loadedQuests;

            } catch (Exception ex) {
                System.err.println("Quests was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
                ex.printStackTrace();
                return null;
            }
        }
        System.err.println("Quests file does not exist! (" + XMLFile.getName() + ")");
        return null;
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

    public String getId() {
        return Quest.getIdFromQuest(this);
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getCompletedDescription(){
        return completeDescription;
    }

    public void applySkipQuestEffects() {
    }


    public int getLevel() {
        return level;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public int getExperienceReward() {
        return experienceReward;
    }
}
