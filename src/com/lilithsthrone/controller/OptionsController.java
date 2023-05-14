package com.lilithsthrone.controller;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishPreference;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.events.EventTarget;

import java.util.Map;

import static com.lilithsthrone.game.settings.Options.*;

/**
 * @author Maxis010, Innoxia
 * @version 0.4.6.4
 * @since 0.4.6.4
 */
public class OptionsController {
    public static void initKeybindListeners() {
        for (KeyboardAction ka : KeyboardAction.values()) {
            if (MainController.document.getElementById("KB_PRIMARY_" + ka) != null) {
                ((EventTarget) MainController.document.getElementById("KB_PRIMARY_" + ka)).addEventListener("click", e -> {
                    MainController.actionToBind = ka;
                    MainController.primaryBinding = true;
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            if (MainController.document.getElementById("KB_PRIMARY_CLEAR_" + ka) != null) {
                ((EventTarget) MainController.document.getElementById("KB_PRIMARY_CLEAR_" + ka)).addEventListener("click", e -> {
                    Main.getProperties().hotkeyMapPrimary.put(ka, null);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            if (MainController.document.getElementById("KB_SECONDARY_" + ka) != null) {
                ((EventTarget) MainController.document.getElementById("KB_SECONDARY_" + ka)).addEventListener("click", e -> {
                    MainController.actionToBind = ka;
                    MainController.primaryBinding = false;
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            if (MainController.document.getElementById("KB_SECONDARY_CLEAR_" + ka) != null) {
                ((EventTarget) MainController.document.getElementById("KB_SECONDARY_CLEAR_" + ka)).addEventListener("click", e -> {
                    Main.getProperties().hotkeyMapSecondary.put(ka, null);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }

    public static void initGenderListeners() {
        for (Gender g : Gender.values()) {
            for (ContentPreferenceValue preference : ContentPreferenceValue.values()) {
                if (MainController.document.getElementById(preference + "_" + g) != null) {
                    ((EventTarget) MainController.document.getElementById(preference + "_" + g)).addEventListener("click", e -> {
                        Main.getProperties().genderPreferencesMap.put(g, preference.getValue());
                        Main.saveProperties();
                        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                    }, false);
                }
            }
        }
    }

    public static void initOrientationListeners() {
        for (SexualOrientation o : SexualOrientation.values()) {
            for (SexualOrientationPreference preference : SexualOrientationPreference.values()) {
                if (MainController.document.getElementById(preference + "_" + o) != null) {
                    ((EventTarget) MainController.document.getElementById(preference + "_" + o)).addEventListener("click", e -> {
                        Main.getProperties().orientationPreferencesMap.put(o, preference.getValue());
                        Main.saveProperties();
                        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                    }, false);
                }
            }
        }
    }

    public static void initFetishListeners() {
        for (AbstractFetish f : Fetish.getAllFetishes()) {
            if (!f.isContentEnabled()) {
                continue;
            }
            for (FetishPreference preference : FetishPreference.values()) {
                String id = preference + "_" + Fetish.getIdFromFetish(f);
                if (MainController.document.getElementById(id) != null) {
                    ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                        Main.getProperties().fetishPreferencesMap.put(f, preference.getValue());
                        Main.saveProperties();
                        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                    }, false);
                    MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                            Util.capitaliseSentence(preference.getName()),
                            preference.getTooltip()));
                }
            }
        }
    }

    public static void initAgeListeners() {
        for (AgeCategory ageCat : AgeCategory.values()) {
            for (PronounType pronoun : PronounType.values()) {
                for (ContentPreferenceValue preference : ContentPreferenceValue.values()) {
                    if (MainController.document.getElementById(pronoun + "_" + preference + "_" + ageCat) != null) {
                        ((EventTarget) MainController.document.getElementById(pronoun + "_" + preference + "_" + ageCat)).addEventListener("click", e -> {
                            Main.getProperties().agePreferencesMap.get(pronoun).put(ageCat, preference.getValue());
                            Main.saveProperties();
                            Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                        }, false);
                    }
                }
            }
        }
    }

    public static void initFurryListeners() {
        String id;
        for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
            id = "SUBSPECIES_PREFERENCE_INFO_" + Subspecies.getIdFromSubspecies(s);
            if (MainController.document.getElementById(id) != null) {
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(s.getName(null)), s.getDescription(null)));
            }
        }

        // Human spawn rates:
        id = "HUMAN_SPAWN_RATE_INCREASE_LARGE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate + 25, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Large Increase", "Increase the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24));
        }
        id = "HUMAN_SPAWN_RATE_INCREASE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate + 5, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Increase", "Increase the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24));
        }
        id = "HUMAN_SPAWN_RATE_DECREASE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate - 5, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Decrease", "Decrease the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24));
        }
        id = "HUMAN_SPAWN_RATE_DECREASE_LARGE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate - 25, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Large Decrease", "Decrease the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24));
        }

        // Taur spawn rates:
        id = "TAUR_SPAWN_RATE_INCREASE_LARGE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate + 25, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Large Increase", "Increase the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24));
        }
        id = "TAUR_SPAWN_RATE_INCREASE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate + 5, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Increase", "Increase the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24));
        }
        id = "TAUR_SPAWN_RATE_DECREASE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate - 5, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Decrease", "Decrease the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24));
        }
        id = "TAUR_SPAWN_RATE_DECREASE_LARGE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate - 25, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Large Decrease", "Decrease the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24));
        }

        // Human spawn rates:
        id = "HALF_DEMON_SPAWN_RATE_INCREASE_LARGE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate + 25, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Large Increase", "Increase the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24));
        }
        id = "HALF_DEMON_SPAWN_RATE_INCREASE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate + 5, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Increase", "Increase the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24));
        }
        id = "HALF_DEMON_SPAWN_RATE_DECREASE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate - 5, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Decrease", "Decrease the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24));
        }
        id = "HALF_DEMON_SPAWN_RATE_DECREASE_LARGE";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate - 25, 100));
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Large Decrease", "Decrease the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24));
        }

        // Taur furry spawns:
        for (int i = 0; i <= 5; i++) {
            int index = i;
            id = "TAUR_FURRY_LIMIT_" + index;
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().taurFurryLevel = index;
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Properties.taurFurryLevelName[index], Properties.taurFurryLevelDescription[index]));
            }
        }

        // Race preferences:
        for (FurryPreference preference : FurryPreference.values()) {
            id = "ALL_FURRY_" + preference;
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
                        Main.getProperties().setFeminineFurryPreference(subspecies, preference);
                        Main.getProperties().setMasculineFurryPreference(subspecies, preference);
                    }
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
        for (SubspeciesPreference preference : SubspeciesPreference.values()) {
            id = "ALL_SPAWN_" + preference;
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
                        Main.getProperties().setFeminineSubspeciesPreference(subspecies, preference);
                        Main.getProperties().setMasculineSubspeciesPreference(subspecies, preference);
                    }
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }

        for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
            String subspeciesId = Subspecies.getIdFromSubspecies(subspecies);
            for (FurryPreference preference : FurryPreference.values()) {
                id = "FEMININE_" + preference + "_" + subspeciesId;
                if (MainController.document.getElementById(id) != null) {
                    if (subspecies.isFurryPreferencesEnabled()) {
                        ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                            Main.getProperties().setFeminineFurryPreference(subspecies, preference);
                            Main.saveProperties();
                            Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                        }, false);
                    }
                    MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                            preference.getName(),
                            subspecies.isFurryPreferencesEnabled()
                                    ? preference.getDescriptionFeminine(subspecies)
                                    : "This subspecies cannot have its furry preference changed!"
                    ));
                }
                id = "MASCULINE_" + preference + "_" + subspeciesId;
                if (MainController.document.getElementById(id) != null) {
                    ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                        if (subspecies.isFurryPreferencesEnabled()) {
                            Main.getProperties().setMasculineFurryPreference(subspecies, preference);
                            Main.saveProperties();
                            Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                        }
                    }, false);
                    MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                            preference.getName(),
                            subspecies.isFurryPreferencesEnabled()
                                    ? preference.getDescriptionMasculine(subspecies)
                                    : "This subspecies cannot have its furry preference changed!"));
                }
            }
            for (SubspeciesPreference preference : SubspeciesPreference.values()) {
                id = "FEMININE_SPAWN_" + preference + "_" + subspeciesId;
                if (MainController.document.getElementById(id) != null) {
                    if (subspecies.isSpawnPreferencesEnabled()) {
                        ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                            Main.getProperties().setFeminineSubspeciesPreference(subspecies, preference);
                            Main.saveProperties();
                            Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                        }, false);
                    }
                    MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                            Util.capitaliseSentence(preference.getName()),
                            subspecies.isSpawnPreferencesEnabled()
                                    ? "Set the weighted chance for feminine genders of this subspecies to spawn. The spawn frequency of '" + preference.getName() + "' has a weight of: <b>" + preference.getValue() + "</b><br/>"
                                    + "<i>This weighting is further affected by map-specific spawn frequencies.</i>"
                                    : "This subspecies cannot have its spawn preference changed!"));
                }
                id = "MASCULINE_SPAWN_" + preference + "_" + subspeciesId;
                if (MainController.document.getElementById(id) != null) {
                    if (subspecies.isSpawnPreferencesEnabled()) {
                        ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                            Main.getProperties().setMasculineSubspeciesPreference(subspecies, preference);
                            Main.saveProperties();
                            Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                        }, false);
                    }
                    MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                            Util.capitaliseSentence(preference.getName()),
                            subspecies.isSpawnPreferencesEnabled()
                                    ? "Set the weighted chance for masculine genders of this subspecies to spawn. The spawn frequency of '" + preference.getName() + "' has a weight of: <b>" + preference.getValue() + "</b><br/>"
                                    + "<i>This weighting is further affected by map-specific spawn frequencies.</i>"
                                    : "This subspecies cannot have its spawn preference changed!"));
                }
            }
        }
    }

    public static void initUnitListeners() {
        createToggleListener("AUTO_LOCALE_ON", PropertyValue.autoLocale, true, () -> {
            Units.FORMATTER.updateSettings();
            Units.FORMATTER.updateFormats(true);
        });
        createToggleListener("AUTO_LOCALE_OFF", PropertyValue.autoLocale, false, () -> {
            Units.FORMATTER.updateSettings();
            Units.FORMATTER.updateFormats(false);

        });
        createToggleListener("METRIC_SIZES_ON", PropertyValue.metricSizes, true, MainController::overrideAutoLocale);
        createToggleListener("METRIC_SIZES_OFF", PropertyValue.metricSizes, false, MainController::overrideAutoLocale);
        createToggleListener("METRIC_FLUIDS_ON", PropertyValue.metricFluids, true, MainController::overrideAutoLocale);
        createToggleListener("METRIC_FLUIDS_OFF", PropertyValue.metricFluids, false, MainController::overrideAutoLocale);
        createToggleListener("METRIC_WEIGHTS_ON", PropertyValue.metricWeights, true, MainController::overrideAutoLocale);
        createToggleListener("METRIC_WEIGHTS_OFF", PropertyValue.metricWeights, false, MainController::overrideAutoLocale);

        Runnable updater = () -> {
            MainController.overrideAutoLocale();
            Units.FORMATTER.updateTimeFormat(false);
        };
        createToggleListener("TWENTYFOUR_HOUR_TIME_ON", PropertyValue.twentyFourHourTime, true, updater);
        createToggleListener("TWENTYFOUR_HOUR_TIME_OFF", PropertyValue.twentyFourHourTime, false, updater);

        updater = () -> {
            MainController.overrideAutoLocale();
            Units.FORMATTER.updateDateFormat(false);
        };
        createToggleListener("INTERNATIONAL_DATE_ON", PropertyValue.internationalDate, true, updater);
        createToggleListener("INTERNATIONAL_DATE_OFF", PropertyValue.internationalDate, false, updater);
    }

	@Deprecated
    static void createMultipleToggleListeners(Map<String, PropertyValue> options) {
		for (Map.Entry<String, PropertyValue> entry : options.entrySet()) {
			OptionsController.createToggleListener(entry.getKey()+"_ON", entry.getValue(), true, null);
			OptionsController.createToggleListener(entry.getKey()+"_OFF", entry.getValue(), false, null);
		}
    }

    public static void createToggleListener(String id, PropertyValue option, boolean value, Runnable action) {
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().setValue(option, value);
                if (action != null) {
                    action.run();
                }
                if (option.isFetishRelated() && Main.game.isStarted()) {
                    Main.game.getPlayer().recalculateAvailableCombatMoves();
                    Main.game.getPlayer().calculateSpecialFetishes();
                    for (GameCharacter character : Main.game.getAllNPCs()) {
                        character.recalculateAvailableCombatMoves();
                        character.calculateSpecialFetishes();
                    }
                }
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
    }

    public static void initMiscellaneousListeners() {
        String id;
        for (int i = 0; i < 3; i++) {
            id = "AUTOSAVE_FREQUENCY_" + i;
            if (MainController.document.getElementById(id) != null) {
                int finalI = i;
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().autoSaveFrequency = finalI;
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Properties.autoSaveLabels[i], Properties.autoSaveDescriptions[i]));
            }
        }

        for (Artist artist : Artwork.allArtists) {
            id = "ARTIST_" + artist.getFolderName();
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().preferredArtist = artist.getFolderName();
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }

        ARTWORK.addListener();
        THUMBNAIL.addListener();
        SHARED_ENCYCLOPEDIA.addListener();
        WEATHER_INTERRUPTION.addListener();
        DIALOGUE_COPY.addListener();
        SILLY.addListener();
    }

    public static void initGameplayListeners() {
        String id;
        for (int i = 0; i < 3; i++) {
            id = "BYPASS_SEX_ACTIONS_" + i;
            if (MainController.document.getElementById(id) != null) {
                int finalI = i;
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().bypassSexActions = finalI;
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Properties.bypassSexActionsLabels[i], Properties.getBypassSexActionsDescriptions[i]));
            }
        }

        for (int i = 0; i < Main.getProperties().clothingFemininityTitles.length; i++) {
            id = "CLOTHING_FEMININITY_" + i;
            int i2 = i; // "Local variable i defined in an enclosing scope must be final or effectively final" ...
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().setClothingFemininityLevel(i2);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Main.getProperties().clothingFemininityTitles[i], Main.getProperties().clothingFemininityDescriptions[i]));
            }
        }
		
        PREGNANCY_DURATION.addListener();
        FORCED_TF.addListener();

        // Forced TF racial limits:
        for (FurryPreference fp : FurryPreference.values()) {
            id = "FORCED_TF_FURRY_LIMIT_" + fp;
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().setForcedTFPreference(fp);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                String title = "";
                String description = "";
                switch (fp) {
                    case HUMAN:
                        title = "Human Only";
                        description = "Forced transformations from NPCs will only ever affect your body's non-racial stats, and if a new part is required (such as a vagina or penis) it will always grow to be a human one.";
                        break;
                    case MINIMUM:
                        title = "Minimum Furry";
                        description = "Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, and wings. All other parts will always remain human.";
                        break;
                    case REDUCED:
                        title = "Lesser Furry";
                        description = "Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, ass, genitalia, arms, and legs. Your skin and face will always remain human.";
                        break;
                    case NORMAL:
                        title = "Greater Furry";
                        description = "Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, ass, genitalia, arms, legs, skin, and face. (So everything can be affected.)";
                        break;
                    case MAXIMUM:
                        title = "Maximum Furry";
                        description = "Forced transformations from NPCs will always give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, genitalia, ass, arms, legs, skin, and face. (So everything will be affected.)";
                        break;
                }
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(title, description));
            }
        }

        // Forced TF Tendency setting events
        for (ForcedTFTendency ftt : ForcedTFTendency.values()) {
            id = "FORCED_TF_TENDENCY_" + ftt;
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().setForcedTFTendency(ftt);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                    ftt.getName(), ftt.getDescription()));
        }

        FORCED_FETISH.addListener();

        // Forced Fetish Tendency setting events
        for (ForcedFetishTendency fft : ForcedFetishTendency.values()) {
            id = "FORCED_FETISH_TENDENCY_" + fft;
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().setForcedFetishTendency(fft);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                    fft.getName(), fft.getDescription()));
        }

        ENCHANTMENT_LIMITS.addListener();
        BAD_END.addListener();
        LEVEL_DRAIN.addListener();
        OPPORTUNISTIC_ATTACKERS.addListener();
        OFFSPRING_ENCOUNTERS.addListener();
        SPITTING_ENABLED.addListener();
        COMPANION.addListener();
    }

    public static void initSexListeners() {
        NON_CON.addListener();
        SADISTIC_SEX.addListener();
        LIPSTICK_MARKING.addListener();
        VOLUNTARY_NTR.addListener();
        INVOLUNTARY_NTR.addListener();
        INCEST.addListener();
        LACTATION.addListener();
        SEXUAL_UDDERS.addListener();
        URETHRAL.addListener();
        NIPPLE_PEN.addListener();
        ANAL.addListener();
        GAPE.addListener();
        PENETRATION_LIMITATION.addListener();
        PENETRATION_LIMITATION_DYNAMIC.addListener();
        FOOT.addListener();
        ARMPIT.addListener();
        MUSK.addListener();
        FURRY_TAIL_PENETRATION.addListener();
        INFLATION_CONTENT.addListener();
        AUTO_SEX_CLOTHING_MANAGEMENT.addListener();
        AUTO_SEX_CLOTHING_STRIP.addListener();
        RAPE_PLAY_BY_DEFAULT.addListener();
    }

    public static void initBodiesListeners() {
        String id;
        for (int i = 0; i < Properties.multiBreastsLabels.length; i++) {
            id = "MULTI_BREAST_PREFERENCE_" + i;
            if (MainController.document.getElementById(id) != null) {
                int finalI = i;
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().multiBreasts = finalI;
                    Main.saveProperties();
                    for (NPC npc : Main.game.getAllNPCs()) {
                        npc.loadImages(true); // Reload images for multi-boob versions
                    }
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                        Properties.multiBreastsLabels[i],
                        Properties.multiBreastsDescriptions[i]));
            }
        }
        for (int i = 0; i < Properties.uddersLabels.length; i++) {
            id = "UDDER_PREFERENCE_" + i;
            if (MainController.document.getElementById(id) != null) {
                int finalI1 = i;
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.getProperties().setUddersLevel(finalI1);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
                MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
                        Properties.uddersLabels[i],
                        Properties.uddersDescriptions[i]
                                + "<br/><i>Characters can always gain udders/crotch-boobs via transformations after they've spawned.</i>"));
            }
        }

        id = "PREGNANCY_BREAST_GROWTH_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyBreastGrowth = Math.min(10, Main.getProperties().pregnancyBreastGrowth + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_BREAST_GROWTH_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyBreastGrowth = Math.max(0, Main.getProperties().pregnancyBreastGrowth - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_BREAST_GROWTH_UDDERS_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderGrowth = Math.min(10, Main.getProperties().pregnancyUdderGrowth + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_BREAST_GROWTH_UDDERS_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderGrowth = Math.max(0, Main.getProperties().pregnancyUdderGrowth - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "PREGNANCY_BREAST_GROWTH_LIMIT_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyBreastGrowthLimit = Math.min(CupSize.getCupSizeFromInt(100).getMeasurement(), Main.getProperties().pregnancyBreastGrowthLimit + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_BREAST_GROWTH_LIMIT_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyBreastGrowthLimit = Math.max(0, Main.getProperties().pregnancyBreastGrowthLimit - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_BREAST_GROWTH_LIMIT_UDDERS_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderGrowthLimit = Math.min(CupSize.getCupSizeFromInt(100).getMeasurement(), Main.getProperties().pregnancyUdderGrowthLimit + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_BREAST_GROWTH_LIMIT_UDDERS_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderGrowthLimit = Math.max(0, Main.getProperties().pregnancyUdderGrowthLimit - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "PREGNANCY_LACTATION_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyLactationIncrease = Math.min(1000, Main.getProperties().pregnancyLactationIncrease + 50);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_LACTATION_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyLactationIncrease = Math.max(0, Main.getProperties().pregnancyLactationIncrease - 50);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_LACTATION_UDDERS_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderLactationIncrease = Math.min(1000, Main.getProperties().pregnancyUdderLactationIncrease + 50);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_LACTATION_UDDERS_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderLactationIncrease = Math.max(0, Main.getProperties().pregnancyUdderLactationIncrease - 50);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "PREGNANCY_LACTATION_LIMIT_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyLactationLimit + 500);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_LACTATION_LIMIT_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyLactationLimit = Math.max(0, Main.getProperties().pregnancyLactationLimit - 500);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_LACTATION_LIMIT_UDDERS_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyUdderLactationLimit + 500);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PREGNANCY_LACTATION_LIMIT_UDDERS_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().pregnancyUdderLactationLimit = Math.max(0, Main.getProperties().pregnancyUdderLactationLimit - 500);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }


        id = "BREAST_SIZE_PREFERENCE_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().breastSizePreference = Math.min(20, Main.getProperties().breastSizePreference + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "BREAST_SIZE_PREFERENCE_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().breastSizePreference = Math.max(-20, Main.getProperties().breastSizePreference - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "BREAST_SIZE_PREFERENCE_UDDERS_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().udderSizePreference = Math.min(20, Main.getProperties().udderSizePreference + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "BREAST_SIZE_PREFERENCE_UDDERS_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().udderSizePreference = Math.max(-20, Main.getProperties().udderSizePreference - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }


        /*id = "PENIS_SIZE_PREFERENCE_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().penisSizePreference = Math.min(20, Main.getProperties().penisSizePreference + 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "PENIS_SIZE_PREFERENCE_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().penisSizePreference = Math.max(-20, Main.getProperties().penisSizePreference - 1);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }*/
        PENIS_SIZE_PREFERENCE.addListener();


        /*id = "TRAP_PENIS_SIZE_PREFERENCE_ON";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().trapPenisSizePreference = Math.min(100, Main.getProperties().trapPenisSizePreference + 10);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
        id = "TRAP_PENIS_SIZE_PREFERENCE_OFF";
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Main.getProperties().trapPenisSizePreference = Math.max(-90, Main.getProperties().trapPenisSizePreference - 10);
                Main.saveProperties();
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }*/
        TRAP_PENIS_SIZE_PREFERENCE.addListener();


        for (Colour colour : PresetColour.getHumanSkinColours()) {
            id = "SKIN_COLOUR_PREFERENCE_" + colour.getId() + "_ON";
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    int newValue = Math.min(10, Main.getProperties().skinColourPreferencesMap.get(colour) + 1);
                    Main.getProperties().skinColourPreferencesMap.put(colour, newValue);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            id = "SKIN_COLOUR_PREFERENCE_" + colour.getId() + "_OFF";
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    int newValue = Math.max(0, Main.getProperties().skinColourPreferencesMap.get(colour) - 1);
                    Main.getProperties().skinColourPreferencesMap.put(colour, newValue);
                    Main.saveProperties();
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }

        AGE.addListener();
        FERAL.addListener();
        CUM_REGENERATION.addListener();
        FUTA_BALLS.addListener();
        CLOACA.addListener();
        VESTIGIAL_MULTI_BREAST.addListener();
        HAIR_FACIAL.addListener();
        HAIR_PUBIC.addListener();
        HAIR_BODY.addListener();
        HAIR_ASS.addListener();
        FEMININE_BEARD.addListener();
        FURRY_HAIR.addListener();
        SCALY_HAIR.addListener();
    }
}