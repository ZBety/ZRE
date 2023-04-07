package com.example.ruleEngine.plugin.management.domain;

import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.plugin.management.repository.PluginManifestRepository;
import com.example.ruleEngine.util.StreamUtil;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PluginManagement {
    private RuleEngineContext ruleEngineContext;
    private PluginManifestRepository repository;
    //工作目录下的plugins
    private String defaultPluginPath = Path.of("plugins").toString();
    private URLClassLoader loader;
    //一个jar对应一个classloader
    private HashMap<String, URLClassLoader> loaders = new HashMap<>();
    private HashMap<String, Plugin> plugins = new HashMap<>();
    private HashMap<String, PluginManifest> manifests = new HashMap<>();

    private PluginContext pluginContext;

    @PostConstruct
    void initManagement() {
        List<PluginManifest> manifestList = StreamUtil.iterableToList(repository.findAll());
        manifests.putAll(manifestList
                .stream()
                .collect(Collectors.toMap(PluginManifest::getId, Function.identity())));
        List<String> fileNames = manifestList.stream().map(PluginManifest::getId).toList();
        installClassLoader(fileNames);
    }

    private void installClassLoader(List<String> fileNames) {
        for (String fileName : fileNames) {
            loadClassloader(fileName);
        }
    }

    private void loadClassloader(String fileName) {
        loadClassloader(defaultPluginPath, fileName);
    }

    private ClassLoader loadClassloader(String dir, String fileName) {
        try {
            File jarFile = new File(dir + "/" + fileName + ".jar");
            URLClassLoader classloader = new URLClassLoader(
                    new URL[]{jarFile.toURI().toURL()},
                    Thread.currentThread().getContextClassLoader()
            );
            loaders.put(fileName, classloader);
            return classloader;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    PluginContext getPluginContext() {
        if (pluginContext == null)
            pluginContext = new DefaultPluginContext(ruleEngineContext);
        return pluginContext;
    }

    PluginManifest createPluginManifest(String name, PluginManifest manifest) {
        manifests.put(name, manifest);
        repository.save(manifest);
        return manifest;
    }

    String updatePath(String dir) {
        defaultPluginPath = dir;
        return defaultPluginPath;
    }

    HashMap<String, Plugin> registeredPlugins() {
        new File(defaultPluginPath).mkdirs();

        loaders.forEach((key, value) -> {
            plugins.put(key, getPlugin(ServiceLoader.load(Plugin.class, value)));

            if (!manifests.containsKey(key)) {
                PluginManifest manifest = new PluginManifest();
                createPluginManifest(key, manifest);
            }
        });

        return plugins;
    }

    void loadManifestPlugin() {
        plugins.forEach((key, value) -> {

        });
    }

    void loadPlugin(Plugin plugin) {
//        PluginManifest manifest = plugin.getManifest();
//        loadPlugin(plugin, manifest);
    }

//    void loadPlugin(Plugin plugin, Manifest manifest) {
//
//    }

    void initPlugin(Plugin plugin) {
//        plugin.init();
    }

    void enablePlugin(Plugin plugin) {

    }

    void enablePlugin() {

    }

    void reload(String key) {
        try {
            loaders.get(key).close();
            loadClassloader(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void unload(String key) {
        try {
            loaders.get(key).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void getVal() {

    }

    Plugin getPlugin(ServiceLoader<Plugin> load) {
        for (Plugin plugin : load) {
            return plugin;
        }
        throw new RuntimeException("plugin error!");
    }

    Plugin getPlugin(String key) {
        return plugins.get(key);
    }

    PluginManifest buildPluginManifest() {
        return null;
    }

    void disablePlugin(Plugin plugin) {

    }

    void restartPlugin(Plugin plugin) {

    }


}
