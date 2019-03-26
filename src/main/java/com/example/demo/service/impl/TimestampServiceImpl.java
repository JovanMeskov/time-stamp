package com.example.demo.service.impl;

import com.example.demo.domain.Timestamp;
import com.example.demo.service.TimestampService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

@Service
public class TimestampServiceImpl implements TimestampService {

    private static final Logger log = LoggerFactory.getLogger(TimestampServiceImpl.class);
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, y", Locale.ENGLISH);

    @Override
    public Timestamp getTimestamp(String date) {
        return convertToNaturalLanguage(date).isPresent() ?
                new Timestamp(Long.valueOf(date), convertToNaturalLanguage(date).get()) :
                convertToUnix(date).isPresent() ? new Timestamp(convertToUnix(date).get(), date) :
                        new Timestamp(null, null);
    }

    private Optional<Long> convertToUnix(String text) {
        try {
            LocalDate localDate = LocalDate.parse(text, formatter);
            ZoneId zoneId = ZoneId.ofOffset("", ZoneOffset.UTC);
            long epoch = localDate.atStartOfDay(zoneId).toEpochSecond();
            return Optional.of(epoch);
        } catch (DateTimeParseException e) {
            log.error("Exception: [{}] when converting to Unix timestamp", e.toString());
        }
        return Optional.empty();
    }

    private Optional<String> convertToNaturalLanguage(String text) {
        try {
            long unixTimeStamp = Long.parseLong(text);
            LocalDate localDate = Instant.ofEpochSecond(unixTimeStamp).atZone(ZoneId.of("UTC")).toLocalDate();
            String naturalLanguageDate = localDate.format(formatter);
            return Optional.of(naturalLanguageDate);
        } catch (NumberFormatException e) {
            log.error("Exception: [{}] when converting to natural language", e.toString());
        }
        return Optional.empty();
    }
}
