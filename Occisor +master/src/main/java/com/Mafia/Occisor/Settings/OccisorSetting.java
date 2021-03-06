package Mafia.Occisor.Settings

// Minecraft.
import net.minecraft.client.Minecraft;

// Java.
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.awt.*;

// Occisor.
import Mafia.Occisor.Occisor;

public class OccisorSetting<T> {
	private OccisorModule master;

	private String name;
	private String tag;
	private String description;

	private SettingType type;

	// All values.
	private boolean value_boolean;

	private String value_string;

	private int value_integer;

	private double value_double;

	private String[] values;

	private double min, max;

	public OccisorSetting(OccisorModule master, String[] details, boolean value) {
		this.master = master;

		this.name        = details[0];
		this.tag         = details[1];
		this.description = details[2];

		this.value_boolean = value;
	}

	public OccisorSetting(OccisorModule master, String[] details, String value) {
		this.master = master;

		this.name        = details[0];
		this.tag         = details[1];
		this.description = details[2];

		this.value_string = value;
	}

	public OccisorSetting(OccisorModule master, String[] details, int value, boolean state) {
		this.master = master;

		this.name        = details[0];
		this.tag         = details[1];
		this.description = details[2];

		this.value_integer = value;
		this.value_boolean = state;
	}

	public OccisorSetting(OccisorModule master, String[] details, int value, int min, int max) {
		this.master = master;

		this.name        = details[0];
		this.tag         = details[1];
		this.description = details[2];

		this.value_integer = value;

		this.min = (double) min;
		this.max = (double) max;
	}

	public OccisorSetting(OccisorModule master, String[] details, double value, double min, double max) {
		this.master = master;

		this.name        = details[0];
		this.tag         = details[1];
		this.description = details[2];

		this.value_double = value;

		this.min = min;
		this.max = max;
	}

	public OccisorSetting(OccisorModule master, String[] details, String value, String[] values) {
		this.master = master;

		this.name        = details[0];
		this.tag         = details[1];
		this.description = details[2];

		this.value_string = value;
		this.values       = values;
	}

	public void setBoolean(boolean value) {
		this.value_boolean = value;
	}

	public void setString(String value) {
		this.value_string = value;
	}

	public void setInteger(int value) {
		this.value_integer = value;
	}

	public void setDouble(double value) {
		this.value_double = value;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public void setType(SettingType type) {
		this.type = type;
	}

	public RocanModule getMaster() {
		return this.master;
	}

	public String getName() {
		return this.name;
	}

	public String getTag() {
		return this.tag;
	}

	public String getDescription() {
		return this.description;
	}

	public SettingType getType() {
		return this.type;
	}

	public boolean getBoolean() {
		return this.value_boolean;
	}

	public String getString() {
		return this.value_string;
	}

	public int getInteger() {
		return this.value_integer;
	}

	public double getDouble() {
		return this.value_double;
	}

	public String[] getList() {
		return this.values;
	}

	public double getMin() {
		return this.min;
	}

	public double getMax() {
		return this.max;
	}

	public enum SettingType {
		SETTING_BOOLEAN,
		SETTING_STRING,
		SETTING_MACRO,
		SETTING_INTEGER,
		SETTING_DOUBLE,
		SETTING_LIST;
	}
}