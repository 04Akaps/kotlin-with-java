package model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MongoTableCollector {
    SampleMflix("sample_mflix");

    public final String dataBaseName;
}