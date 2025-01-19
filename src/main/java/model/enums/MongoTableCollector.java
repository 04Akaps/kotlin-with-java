package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MongoTableCollector {
    SampleMflix("sample_mflix");

    private final String dataBaseName;
}