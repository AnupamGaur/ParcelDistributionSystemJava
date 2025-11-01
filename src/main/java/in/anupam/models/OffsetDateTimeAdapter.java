package in.anupam.models;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.OffsetDateTime;

public class OffsetDateTimeAdapter extends XmlAdapter<String, OffsetDateTime> {
    @Override
    public OffsetDateTime unmarshal(String v) throws Exception {
        return v == null || v.isEmpty() ? null : OffsetDateTime.parse(v);
    }

    @Override
    public String marshal(OffsetDateTime v) throws Exception
    {
        return v == null ? null : v.toString();
    }
}
