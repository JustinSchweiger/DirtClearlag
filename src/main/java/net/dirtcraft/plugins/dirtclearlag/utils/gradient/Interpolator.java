package net.dirtcraft.plugins.dirtclearlag.utils.gradient;

@FunctionalInterface
public interface Interpolator {

	double[] interpolate(double from, double to, int max);
}
