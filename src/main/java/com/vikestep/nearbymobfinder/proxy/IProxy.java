package com.vikestep.nearbymobfinder.proxy;

public interface IProxy {
	public abstract void registerEventHandlers();
	public abstract void registerKeyBindings();
	public abstract void registerItemsAndBlocks();
	/** Needs to be called before {@link #registerItemsAndBlocks} and creation of any fluid blocks. */
	public abstract void registerFluids();
	public abstract void registerRenderers();
	public abstract void registerOreDict();
	public abstract void registerRecipes();
}
