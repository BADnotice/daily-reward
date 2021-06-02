package io.github.badnotice.dailyreward.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RewardType {

    COMMAND,
    ITEM,
    ALL;

    public static RewardType find(String typeName) {
        return Arrays.stream(values())
                .filter(rewardType -> rewardType.toString().equalsIgnoreCase(typeName))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Invalid type {" + typeName + "} for reward!"));
    }

}
