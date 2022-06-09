package com.skillafire.antiquita.blockentity.common;

public class ItemSlotTypesEnum {
	public static final int INPUT = Integer.parseUnsignedInt("0000001", 2);
	public static final int OUTPUT = Integer.parseUnsignedInt("0000010", 2);
	public static final int ITEM = Integer.parseUnsignedInt("0000100", 2);
	public static final int FLUID = Integer.parseUnsignedInt("0001000", 2);
	public static final int ENERGY = Integer.parseUnsignedInt("0010000", 2);
	public static final int FUELONLY = Integer.parseUnsignedInt("0100000", 2);
	public static final int FUELEXCLUDED = Integer.parseUnsignedInt("1000000", 2);
	
	public static final Boolean checkMaskEquals(int flag, int mask) {
		return (flag & mask) == mask;
	}
	
	public static final Boolean checkMaskNotEquals(int flag, int mask) {
		return (flag & mask) != mask;
	}
}
