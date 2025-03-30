# 

<div align="center">

![](docs/src/static/fancyplugins-banner.png)

[![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/available/modrinth_vector.svg)](https://modrinth.com/user/Oliver)
[![Hangar](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/available/hangar_vector.svg)](https://hangar.papermc.io/Oliver)

[![Lines of Code](https://tokei.rs/b1/github/fancymcplugins/fancyplugins?category=code)](https://github.com/fancymcplugins/fancyplugins)
[![Discord](https://img.shields.io/discord/899740810956910683?cacheSeconds=3600&logo=discord&logoColor=white&label=%20&labelColor=%235865F2&color=%23707BF4)](https://discord.gg/ZUgYCEJUEx)

<br />

Simple, lightweight and feature-rich plugins for Minecraft servers.

</div>

## Modules

> [!TIP]
> Read the [monorepo.md](docs/src/development-guidelines/monorepo.md) for more information about the monorepo.
> Here is a blog article about why we use a monorepo: [why-monorepo.md](docs/src/blog/why-monorepo.md).

Plugins:
- fancynpcs: create and manage fancy looking npcs ([old repository](https://github.com/FancyMcPlugins/FancyNpcs))
- fancyholograms (v2): create fancy looking holograms ([old repository](https://github.com/FancyMcPlugins/FancyHolograms))
- fancyholograms (v3): create fancy looking holograms
- fancyvisuals: plugin to customize all visual components of the game

Libraries:
- common: common classes and utilities (FancyLib previously)
- jdb: json database library (included in FancyLib previously)
- plugin-tests: testing library for plugins (included in FancyLib previously)
- packets: packet handling library (FancySitula previously)

Tools:
- deployment: deploy plugins to platforms like modrinth
- quick-e2e: generate a quick end-to-end environment for testing

Docs: documentation for all plugins, libraries and tools.

Future packages that are planned to be added to the monorepo:
- fancywebsite: https://github.com/FancyMcPlugins/FancyWebsite
- (fancymorphs: https://github.com/FancyMcPlugins/FancyMorphs)
- modrinth-sdk: an SDK for the modrinth API (which then can be used in the deployment and quick-e2e tool)
- minecraft-client: a lightweight Minecraft client for testing

## Usage

The monorepo uses Gradle as build system. See [monorepo.md](docs/src/development-guidelines/monorepo.md) for more information.

To see specific usage for each package, see the README.md in the respective package directory.

## Contributors

Since this is a new repository, the list of contributors didn't get migrated from the previous repositories.
Here is a list of contributors to the previous repositories:

- [Grabsky](https://github.com/Grabsky)
- [OakLoaf](https://github.com/OakLoaf)
- [Matt-MX](https://github.com/MattMX)
- [Sxtanna](https://github.com/Sxtanna)
- [airoons](https://github.com/airoons)
- [BitByLogics](https://github.com/BitByLogics)
- [devpeaqe](https://github.com/devpeaqe)
- [NonSwag](https://github.com/NonSwag)
- [FrezzyDev](https://github.com/FrezzyDev)
- [perkojack](https://github.com/perkojack)
- [BigTowns](https://github.com/BigTowns)
- [kforbro](https://github.com/kforbro)
- [Kamillaova](https://github.com/Kamillaova)
- [Emibergo02](https://github.com/Emibergo02)
- [bridgelol](https://github.com/bridgelol)
- [Seshpenguin](https://github.com/Seshpenguin)
- [UltraFaceguy](https://github.com/UltraFaceguy)
- [4drian3d](https://github.com/4drian3d)
- [Zemux1613](https://github.com/Zemux1613)
- [Yive](https://github.com/Yive)
- [UsainSrht](https://github.com/UsainSrht)
- [killerprojecte](https://github.com/killerprojecte)
- [SirSalad](https://github.com/SirSalad)
- [yukisnowflake](https://github.com/yukisnowflake)
- [BiFTHg](https://github.com/BiFTHg)
- [alexcastro1919](https://github.com/alexcastro1919)
- [FrinshHD](https://github.com/FrinshHD)
- [xii69](https://github.com/xii69)
- [ruViolence](https://github.com/ruViolence)
- [JonasDevDE](https://github.com/JonasDevDE)
- [CloveTwilight3](https://github.com/CloveTwilight3)

*(Taken from https://github.com/FancyMcPlugins/FancyNpcs/graphs/contributors and https://github.com/FancyMcPlugins/FancyHolograms/graphs/contributors)*

If you contributed to the previous repositories and want to be listed here, please open an issue or a pull request.