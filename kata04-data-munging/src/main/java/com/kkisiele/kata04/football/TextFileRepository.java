package com.kkisiele.kata04.football;

import com.kkisiele.kata04.parser.ClassPathResource;
import com.kkisiele.kata04.parser.TextFileParser;
import com.kkisiele.kata04.parser.TextFileRow;

import java.util.ArrayList;
import java.util.List;

class TextFileRepository implements Repository {
    private static final String FOOTBALL_DATA_FILE = "football.dat";

    private static final String TEAM_NAME_HEADER = "Team";
    private static final String SCORED_GOALS_HEADER = "F";
    private static final String CONCEDED_GOALS_HEADER = "A";

    @Override
    public List<Team> getAllTeams() {
        List<Team> result = new ArrayList<>();
        for(TextFileRow row : parseDataRows()) {
            result.add(new TeamImpl(row));
        }
        return result;
    }

    private List<TextFileRow> parseDataRows() {
        TextFileParser parser = new TextFileParser(new ClassPathResource(FOOTBALL_DATA_FILE));
        parser.addIgnoreLinePredicate(line -> line.trim().startsWith("---"));
        return parser.dataRows();
    }

    private class TeamImpl extends Team {
        private final TextFileRow row;

        public TeamImpl(TextFileRow row) {
            this.row = row;
        }

        @Override
        public String name() {
            return row.getString(TEAM_NAME_HEADER);
        }

        @Override
        public int scoredGoals() {
            return row.getInteger(SCORED_GOALS_HEADER);
        }

        @Override
        public int concededGoals() {
            return row.getInteger(CONCEDED_GOALS_HEADER);
        }
    }
}