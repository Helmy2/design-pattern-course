package com.course.patterns.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * STARTER CODE:
 * TODO: Implement deep copy constructor and Prototype<NetworkSecurityProfile>.
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

    // TODO: Step 1 - Implement Deep Copy Constructor:
    // Create a new ArrayList for firewallRules so references are completely isolated
    public NetworkSecurityProfile(NetworkSecurityProfile target) {
        if (target != null) {
            this.profileName = target.profileName;
            this.maxBandwidthMbps = target.maxBandwidthMbps;
            // TODO: allocate new list
            this.firewallRules = target.firewallRules;
        }
    }

    @Override
    public NetworkSecurityProfile clone() {
        // TODO: Step 2 - Return new deep copied instance
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
