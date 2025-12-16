/**
 * Title:        DepthFirstSearchEngine<p>
 * Description:  Performs a depth first search in a maze<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

import java.awt.Dimension;

public class DepthFirstSearchEngine extends AbstractSearchEngine {
    private boolean optimizado;
	
	public DepthFirstSearchEngine(int width, int height, boolean optimizado) {
        super(width, height);
        this.optimizado = optimizado;
        
        iterateSearch(startLoc, 1);        
    }

    private void iterateSearch(Dimension loc, int depth) {
    	Dimension [] moves;
    	if (isSearching == false) return;
        maze.setValue(loc.width, loc.height, (short)depth);
        if (optimizado) moves=getPossibleMovesOptimizado(loc);
        else moves=getPossibleMoves(loc);
                
        for (int i=0; i<4; i++) {
            if (moves[i] == null) break; // out of possible moves from this location
            searchPath[depth] = moves[i];
            if (equals(moves[i], goalLoc)) {
                isSearching = false;
                maxDepth = depth;
                return;
            } else {
                iterateSearch(moves[i], depth + 1);
                if (isSearching == false) return;
            }
        }
        return;
    }
}
