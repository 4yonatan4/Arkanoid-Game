package score;

import java.util.Comparator;

/**
 * The type Sort comparator.
 */
public class SortComparator implements Comparator<ScoreInfo> {

    @Override
    public int compare(ScoreInfo o1, ScoreInfo o2) {
        return (o2.getScore() - o1.getScore());
    }
}
