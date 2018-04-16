package com.kkisiele.datamunging.weather;

import java.util.List;

interface Repository {
    List<Measurement> getAllMeasurements();
}