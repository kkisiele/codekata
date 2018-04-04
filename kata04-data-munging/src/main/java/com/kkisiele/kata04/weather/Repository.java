package com.kkisiele.kata04.weather;

import java.util.List;

interface Repository {
    List<Measurement> getAllMeasurements();
}