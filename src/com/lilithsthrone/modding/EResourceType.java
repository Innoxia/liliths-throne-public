package com.lilithsthrone.modding;

import java.nio.file.Path;

/**
 * This hellish little enum provides signals to GameResource and ModInfo on how they handle certain files.
 * @author Anonymous-BCFED
 */
public enum EResourceType {
    // Util.getExternalModFilesById("/race", "coveringTypes", null);
    BODY_COVERING("/race", "coveringTypes", null, null),

    // Util.getExternalModFilesById("/race", "bodyParts", null);
    // if(Util.getXmlRootElementName(innerEntry.getValue()).equals("antenna")) {
    ANTENNA("/race", "bodyParts", null, "antenna"),
    ANUS("/race", "bodyParts", null, "anus"),
    ARM("/race", "bodyParts", null, "arm"),
    BREAST("/race", "bodyParts", null, "breast"),
    EAR("/race", "bodyParts", null, "breast"),
    EYE("/race", "bodyParts", null, "eye"),
    FACE("/race", "bodyParts", null, "face"),
    FLUID("/race", "bodyParts", null, "fluid"),
    HAIR("/race", "bodyParts", null, "hair"),
    HORN("/race", "bodyParts", null, "horn"),
    LEG("/race", "bodyParts", null, "leg"),
    MOUTH("/race", "bodyParts", null, "mouth"),
    NIPPLE("/race", "bodyParts", null, "nipple"),
    PENIS("/race", "bodyParts", null, "penis"),
    TAIL("/race", "bodyParts", null, "tail"),
    TENTACLE("/race", "bodyParts", null, "tentacle"),
    TESTICLE("/race", "bodyParts", null, "testicle"),
    TONGUE("/race", "bodyParts", null, "tongue"),
    TORSO("/race", "bodyParts", null, "torso"),
    VAGINA("/race", "bodyParts", null, "vagina"),
    WING("/race", "bodyParts", null, "wing"),

    // Util.getExternalModFilesById("/statusEffects")
    STATUS_EFFECT("/statusEffects", null, null, null),

    // Util.getExternalModFilesById("/items/tattoos");
    TATTOO("/items/tattoos", null, null, null),

    // Util.getExternalModFilesById("/race", null, "race");
    // if(Util.getXmlRootElementName(innerEntry.getValue()).equals("race")) {
    RACE("/race", null, "race", "race"),

    // Util.getExternalModFilesById("/race", null, "racialBody");
    RACIAL_BODY("/race", null, "racialBody", null),

    // Util.getExternalModFilesById("/race", "subspecies", null);
    // if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
    SUBSPECIES("/race", "subspecies", null, "subspecies"),

    // Util.getExternalModFilesById("/combatMove");
    COMBAT_MOVE("/combatMove", null, null, null),

    // Util.getExternalModFilesById("/dialogue", null, "flags");
    DIALOGUE_FLAG_VALUE("/dialogue", null, "flags", null),

    // Util.getExternalModFilesById("/dialogue");
    DIALOGUE("/dialogue", null, null, null),

    // Util.getExternalModFilesById("/encounters");
    ENCOUNTER("/encounters", null, null, null),

    // Util.getExternalModFilesById("/setBonuses");
    SET_BONUS("/setBonuses", null, null, null),

    // Util.getExternalModFilesById("/items/clothing");
    CLOTHING("/item/clothing", null, null, null),

    // Util.getExternalModFilesById("/items/items");
    ITEM("/item/items", null, null, null),

    // Util.getExternalModFilesById("/outfits");
    OUTFIT("/outfits", null, null, null),

    // Util.getExternalModFilesById("/items/weapons");
    WEAPON("/items/weapons", null, null, null),

    // Util.getExternalModFilesById("/sex/managers");
    SEX_MANAGER("/sex/managers", null, null, null),

    // Util.getExternalModFilesById("/items/patterns");
    PATTERN("/items/patterns", null, null, null),

    // Util.getExternalModFilesById("/colours");
    COLOUR("/colours", null, null, null),

    // Util.getExternalModFilesById("/maps", null, "worldType");
    WORLD("/maps", null, "worldType", null),

    // Util.getExternalModFilesById("/maps", "placeTypes", null);
    PLACE("/maps", "placeTypes", null, null)
    ;
    
    private Path searchDir;
    private String idPrefix;
    private String xmlRootFilter;
    private String idMustMatch;
    EResourceType(String searchDir, String idPrefix, String idMustMatch, String xmlRootFilter) {
        this.searchDir = Path.of(searchDir+"/");
        this.idPrefix = idPrefix;
        this.idMustMatch = idMustMatch;
        this.xmlRootFilter = xmlRootFilter;
    }
    public String getIdPrefix() {
        return idPrefix;
    }
    public String getSearchDir() {
        return searchDir;
    }
    public String getXMLRootFilter() {
        return xmlRootFilter;
    }
    public String getIdMustMatch() {
        return idMustMatch;
    }

    public boolean resourceMatches(GameResource rsc) {
        // IMPORTANT: rsc.type is not filled in at this point.
        // This method helps determine its content.
        
        assert this.searchDir != null;

        Path rscPath = rsc.getAbsolutePath();
        Path relPath = rsc.getRelativePath();

        if(!relPath.startsWith(this.searchDir+"/"))
            return false;

        if(this.idPrefix != null && )
    }
}
