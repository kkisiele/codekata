package com.kkisiele.kata04.football;

import com.kkisiele.kata04.parser.ClassPathResource;
import com.kkisiele.kata04.parser.TextFileParser;
import com.kkisiele.kata04.parser.TextFileRow;

import java.util.ArrayList;
import java.util.List;

class TextFileRepository implements Repository {
    private final TextFileParser parser;

    public TextFileRepository() {
        parser = new TextFileParser(new ClassPathResource("football.dat"));
        parser.addIgnoreLinePredicate(line -> line.trim().startsWith("---"));
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> result = new ArrayList<>();
        for(TextFileRow row : parser.parse().lines()) {
            result.add(new TeamImpl(row));
        }
        return result;
    }

    private class TeamImpl extends Team {
        private final TextFileRow row;

        public TeamImpl(TextFileRow row) {
            this.row = row;
        }

        @Override
        public String name() {
            return row.getString("Team");
        }

        @Override
        public int scoredGoals() {
            return row.getInteger("F");
        }

        @Override
        public int concededGoals() {
            return row.getInteger("A");
        }
    }
}