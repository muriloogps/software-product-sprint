// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public final class FindMeetingQuery {
    public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
        if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
            return Arrays.asList();
        }
        if (events.isEmpty()) {
            return Arrays.asList(TimeRange.WHOLE_DAY);
        }

        int differentAttendees = 1;

        List<TimeRange> available = Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TimeRange.END_OF_DAY, true));
        // Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TIME_0800AM, false),
        //     TimeRange.fromStartEnd(TIME_0830AM, TIME_0900AM, false),
        //     TimeRange.fromStartEnd(TIME_0930AM, TimeRange.END_OF_DAY, true));
        for (Event event : events) {
            if (attendeesOverlap(event.getAttendees(), request.getAttendees())) {
                available = remove(available, event.getWhen());
                differentAttendees = 0;
            }
        }

        if (differentAttendees == 1) {
            return Arrays.asList(TimeRange.WHOLE_DAY);
        }
        return available;
    }

    public List<TimeRange> remove(Collection<TimeRange> collectionRanges, TimeRange rangeToRemove) {
        int index = -1;
        ArrayList<TimeRange> ranges = new ArrayList<>(collectionRanges);
        for (TimeRange range : ranges) {
            
            index++;

            // Case 1: range is fully contained in rangeToRemove
            //   |---|            range
            // |--------|         rangeToRemove
            if (range.start() >= rangeToRemove.start() && range.end() <= rangeToRemove.end()) {
                ranges.remove(index);
                index--;
                continue;
            }

            // Case 2: range starts before but end while rangeToRemove is happening
            //   |------|         range
            //      |--------|    rangeToRemove
            if (range.start() < rangeToRemove.start() && range.end() >= rangeToRemove.start() && range.end() <= rangeToRemove.end()) {
                TimeRange newRange = TimeRange.fromStartEnd(range.start(), rangeToRemove.start(), false);
                ranges.set(index, newRange);
                continue;
            }

            // Case 3: range ends after but starts while rangeToRemove is happening
            //   |--------|       range
            // |------|           rangeToRemove
            if (range.start() >= rangeToRemove.start() && range.start() <= rangeToRemove.end() && range.end() > rangeToRemove.end()) {
                TimeRange newRange = TimeRange.fromStartEnd(rangeToRemove.end(), range.end(), false);
                ranges.set(index, newRange);
                continue;
            }

            // Case 4: range fully covers rangeToRemove
            // |---------|       range
            //   |----|          rangeToRemove
            if (range.start() < rangeToRemove.start() && range.end() > rangeToRemove.end()) {
                TimeRange leftRange = TimeRange.fromStartEnd(range.start(), rangeToRemove.start(), false);
                TimeRange rightRange;
                if (range.end() >= TimeRange.END_OF_DAY) {
                   rightRange = TimeRange.fromStartEnd(rangeToRemove.end(), TimeRange.END_OF_DAY, true);
                } else {
                    rightRange = TimeRange.fromStartEnd(rangeToRemove.end(), range.end(), true);
                }
                ranges.set(index, leftRange);
                ranges.add(index+1, rightRange);
                break;
            }

        }

        return ranges;
    }

    public boolean attendeesOverlap(Set<String> setA, Set<String> setB) {
        Set<String> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        if (intersection.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}