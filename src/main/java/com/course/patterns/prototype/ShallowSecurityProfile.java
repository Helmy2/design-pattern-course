package com.course.patterns.prototype;

import java.util.List;

/**
 * STARTER CODE:
 * Shallow Copy Bug: firewallRules reference is shared between original and clone!
 */
public class ShallowSecurityProfile implements Prototype<ShallowSecurityProfile> {
    private String profileName;
    private int maxBandwidthMbps;
    private List<String> firewallRules;

    public ShallowSecurityProfile(String profileName, int maxBandwidthMbps, List<String> firewallRules) {
        this.profileName = profileName;
        this.maxBandwidthMbps = maxBandwidthMbps;
        this.firewallRules = firewallRules;
    }

    // Shallow copy constructor
    public ShallowSecurityProfile(ShallowSecurityProfile target) {
        this.profileName = target.profileName;
        this.maxBandwidthMbps = target.maxBandwidthMbps;
        this.firewallRules = target.firewallRules; // Shared reference bug!
    }

    @Override
    public ShallowSecurityProfile clone() {
        return new ShallowSecurityProfile(this);
    }

    public void addRule(String rule) {
        firewallRules.add(rule);
    }

    public String getProfileName() { return profileName; }
    public void setProfileName(String name) { this.profileName = name; }
    public List<String> getFirewallRules() { return firewallRules; }
}
