package com.course.patterns.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * Typed Prototype Pattern with Deep Copying:
 * - Avoids Java's flawed Cloneable interface.
 * - Deep copy constructor allocates a distinct ArrayList on heap for collections,
 *   preventing shared mutable state bugs between prototypes and clones.
 */
public class NetworkSecurityProfile implements Prototype<NetworkSecurityProfile> {

    private String profileName;
    private int maxBandwidthMbps;
    private List<String> firewallRules;

    public NetworkSecurityProfile(String profileName, int maxBandwidthMbps, List<String> firewallRules) {
        this.profileName = profileName;
        this.maxBandwidthMbps = maxBandwidthMbps;
        this.firewallRules = new ArrayList<>(firewallRules);
    }

    // Deep Copy Constructor
    public NetworkSecurityProfile(NetworkSecurityProfile target) {
        if (target != null) {
            this.profileName = target.profileName;
            this.maxBandwidthMbps = target.maxBandwidthMbps;
            // Complete memory isolation (Deep Copy)
            this.firewallRules = new ArrayList<>(target.firewallRules);
        }
    }

    @Override
    public NetworkSecurityProfile clone() {
        return new NetworkSecurityProfile(this);
    }

    public void addRule(String rule) {
        this.firewallRules.add(rule);
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setMaxBandwidthMbps(int maxBandwidthMbps) {
        this.maxBandwidthMbps = maxBandwidthMbps;
    }

    public String getProfileName() { return profileName; }
    public int getMaxBandwidthMbps() { return maxBandwidthMbps; }
    public List<String> getFirewallRules() { return Collections.unmodifiableList(firewallRules); }
}
