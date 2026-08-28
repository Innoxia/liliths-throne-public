package com.lilithsthrone.modding;

import java.io.File;
import java.nio.file.Path;

/**
 * Represents everything the game could need to know about a game data file.
 * @author Anonymous-BCFED
 * @since FIXME
 */
public class GameResource {
    /**
     * UNIQUE ID of this resource.
     */
    public String id;

    /**
     * Where this resource is stored on-disk.
     */
    public File file;

    /**
     * Which kind of resource this is.
     * 
     * This property determines how it is treated by the game.
     */
    public EResourceType type = null; 

    /**
     * Which mod this resource was loaded by.
     * 
     * Can be null.
     */
    public ModInfo mod;

    private Path _absPath = null;
    private Path _relPath = null;

    public Path getAbsolutePath() {
        if(this._absPath == null) {
            this._absPath = this.file.toPath().toAbsolutePath();
        }
        return this._absPath;
    }

    public Path getRelativePath() {
        if(this._relPath == null) {
            Path absPath = this.getAbsolutePath();
            Path basePath = this.mod.modDir.toPath().toAbsolutePath();
            this._relPath = basePath.relativize(absPath);
        }
        return this._relPath;
    }
    
}
