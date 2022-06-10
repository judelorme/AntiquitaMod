package com.skillafire.antiquita.blockentity.common;

public class ItemSlotTypesEnum {
	public static final int INPUT = Integer.parseUnsignedInt("00000001", 2);
	public static final int OUTPUT = Integer.parseUnsignedInt("00000010", 2);
	public static final int ITEM = Integer.parseUnsignedInt("00000100", 2);
	public static final int FLUID = Integer.parseUnsignedInt("00001000", 2);
	public static final int ENERGY = Integer.parseUnsignedInt("00010000", 2);
	public static final int FUELONLY = Integer.parseUnsignedInt("00100000", 2);
	public static final int FUELEXCLUDED = Integer.parseUnsignedInt("01000000", 2);
	public static final int UPGRADE = Integer.parseUnsignedInt("10000000", 2);
	
	public static final Boolean checkMaskEquals(int flag, int mask) {
		return (flag & mask) == mask;
	}
	
	public static final Boolean checkMaskNotEquals(int flag, int mask) {
		return (flag & mask) != mask;
	}
}
