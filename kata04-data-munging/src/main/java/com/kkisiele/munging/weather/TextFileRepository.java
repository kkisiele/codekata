package com.kkisiele.munging.weather;

import com.kkisiele.munging.parser.ClassPathResource;
import com.kkisiele.munging.parser.TextFileParser;
import com.kkisiele.munging.parser.TextFileRow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class TextFileRepository implements Repository {
    private static final String WEATHER_DATA_FILE = "weather.dat";

    private static final String DAY_OF_MONTH_HEADER = "Dy";
    private static final String MINIMUM_TEMPERATURE_HEADER = "MnT";
    private static final String MAXIMUM_TEMPERATURE_HEADER = "MxT";
    private static final String SUMMARY_ROW = "mo";

    @Override
    public List<Measurement> getAllMeasurements() {
        List<Measurement> result = new ArrayList<>();
        for(TextFileRow row : parseDataRows()) {
            if(!isSummaryRow(row)) {
                result.add(new MeasurementImpl(row));
            }
        }
        return result;
    }

    private List<TextFileRow> parseDataRows() {
        TextFileParser parser = new TextFileParser(new ClassPathResource(WEATHER_DATA_FILE));
        return parser.dataRows();
    }

    private boolean isSummaryRow(TextFileRow row) {
        return row.getString(DAY_OF_MONTH_HEADER).equals(SUMMARY_ROW);
    }

    private class MeasurementImpl implements Measurement {
        private final TextFileRow row;

        public MeasurementImpl(TextFileRow row) {
            this.row = row;
        }

        @Override
        public int dayOfMonth() {
            return row.getInteger(DAY_OF_MONTH_HEADER);
        }

        @Override
        public Temperature minTemperature() {
            return parseTemperature(MINIMUM_TEMPERATURE_HEADER);
        }

        @Override
        public Temperature maxTemperature() {
            return parseTemperature(MAXIMUM_TEMPERATURE_HEADER);
        }

        private Temperature parseTemperature(String headerName) {
            return Temperature.fahrenheit(parseBigDecimal(headerName));
        }

        private BigDecimal parseBigDecimal(String headerName) {
            String value = row.getString(headerName);
            if(value == null) {
                return null;
            }
            value = value.replaceAll("\\*", "");
            return new BigDecimal(value);
        }
    }
}