package com.kkisiele.kata04.football;

import com.kkisiele.kata04.parser.ClassPathResource;
import com.kkisiele.kata04.parser.TextFileParser;
import com.kkisiele.kata04.parser.TextFileRow;

import java.util.ArrayList;
import java.util.List;

class FileRepository implements Repository {
    private final TextFileParser parser;

    public FileRepository() {
        parser = new TextFileParser(new ClassPathResource("football.dat"));
        parser.addIgnorableLinePredicate(line -> line.trim().startsWith("---"));
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
        private TextFileRow row;

        public TeamImpl(TextFileRow row) {
            this.row = row;
        }

        @Override
        public String name() {
            return row.stringField("Team");
        }

        @Override
        public int scoredGoals() {
            return Integer.parseInt(row.stringField("F"));
        }

        @Override
        public int concededGoals() {
            return Integer.parseInt(row.stringField("A"));
        }
    }
}