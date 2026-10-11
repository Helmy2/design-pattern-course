package com.course.patterns.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * STARTER PROBLEM CODE:
 *
 * NetworkSecurityProfile before applying the Prototype Pattern:
 *
 * 1. Heavy Configuration & Concrete Coupling:
 *    To instantiate or duplicate profiles for different tenants, client code is forced
 *    to directly invoke 'new NetworkSecurityProfile(...)'.
 *
 * 2. Manual Property Copying:
 *    Without a standardized clone() contract, clients must manually extract every property
 *    via getters and construct a duplicate from outside, violating encapsulation.
 *
 * 3. Cannot Duplicate via Abstractions:
 *    If client code only holds a high-level reference to a profile, it cannot create a copy
 *    without coupling to concrete classes and performing awkward type checks.
 */
public class NetworkSecurityProfile {

    private String profileName;
    private int maxBandwidthMbps;
    private List<String> firewallRules;

    public NetworkSecurityProfile(String profileName, int maxBandwidthMbps, List<String> firewallRules) {
        this.profileName = profileName;
        this.maxBandwidthMbps = maxBandwidthMbps;
        this.firewallRules = new ArrayList<>(firewallRules);
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
