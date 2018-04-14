package com.kkisiele.munging.weather;

import java.util.List;

interface Repository {
    List<Measurement> getAllMeasurements();
}