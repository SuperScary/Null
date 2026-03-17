---
navigation:
  parent: items-and-blocks/items-and-blocks-index.md
  title: Dimensional Quarry
  position: 10
---

# Dimensional Quarry
The Dimensional Quarry is a late-game automated miner that excavates resources in a dedicated quarry dimension and exports the drops back to your base. The Dimensional Quarry is able to pick up blocks, items, and fluids from the quarry dimension.

## Mining Dimension
The mining dimension is a form of the overworld/nether/end that the quarry uses to mine resources. It is a separate dimension from the overworld/nether/end that you are currently in. This dimension is inaccessible through conventional means. 

<GameScene zoom="4" interactive={true}>
  <ImportStructure src="nullmod:assets/assemblies/dimensional_quarry.nbt" />
  <IsometricCamera yaw="195" pitch="30" />
</GameScene>

## Building the multiblock

The Dimensional Quarry consists of:

- **[Dimensional Quarry Frame](dimensional-quarry-frame.md)**
- **[6 Dimensional Quarry Satellites](dimensional-quarry-satellite.md)**

To form the quarry:

- Place the **Frame**.
- Place a **Satellite** on *each of the six sides* of the Frame (up, down, north, south, east, west).
- Satellites must be placed so they **face the Frame**.

Notes:

- Satellites can only be placed if you click a face of the Frame (the Satellite block validates placement).
- If any Satellite is missing or facing the wrong way, the quarry is **not formed** and cannot be used.

## Opening the GUI

When the multiblock is formed:

- Right-click the **Frame**, or
- Right-click any **Satellite**

to open the same quarry GUI.

If the multiblock is not formed, the Frame will not open.

## Redstone control

If the Frame receives a redstone signal, the quarry will **disable itself**.

## Power, fluids, and exports

### Energy

- Internal buffer: **20,000,000 FE**
- Max input/output: **200,000 FE/t**
- Mining cost: **20,000 FE per block**

If the quarry has insufficient energy it will pause with the status **"No energy"**.

### Export inventory

Drops are inserted into an internal **9-slot export inventory**.

- If the export fills up, the quarry pauses with **"Export full"**.
- You must extract items from the export using automation.

### Fluid tank

The quarry has an internal **10,000 mB** tank:

- **Source fluids** are collected into the tank (1 bucket / 1000 mB at a time).
- **Flowing fluids** are skipped.
- If the tank is full, it will skip source fluids and show **"Fluid tank full (skipping source fluids)"**.

## Quarry upgrades (GUI slots)

The quarry GUI contains 3 special slots:

### 1) Biome Marker

Accepts the **Biome Marker** item (`nullmod:biome_marker`).

This is used by the quarry to select what biome it should simulate/use for mining (the quarry tracks a selected biome id internally).

### 2–3) Enchanted Books

The two book slots accept **Enchanted Books**. The quarry combines the enchantments from both books onto its internal mining tool:

- The tool is a **Diamond Pickaxe**
- Only enchantments that are valid for a diamond pickaxe are accepted

This affects what drops you get (e.g., Fortune/Silk Touch depending on what you insert).

## How mining works

### The quarry dimension

Mining does not happen in your world directly. The quarry mines inside a dedicated dimension:

- `nullmod:quarry_overworld`

If this dimension is missing, the quarry will stop with **"Quarry dimension missing"**.

### Chunk assignment (unique per quarry)

Each quarry instance generates a random **instance salt** on first activation. Combined with the world seed, this salt is used to deterministically pick a starting chunk.

The quarry then claims a chunk from a global save-data list so different quarries don’t overlap.

### Cursor scanning

The quarry mines **one block per tick attempt** and advances a cursor:

- It mines from the **surface downward** in a column.
- When it reaches min build height, it moves to the next column.
- After completing a 16×16 area, it advances to another claimed chunk.

### What happens to mined blocks

- Air and bedrock are skipped.
- Mined blocks are replaced with **bedrock** in the quarry dimension.
- Drops are generated using normal loot tables with the quarry’s internal tool.

## Troubleshooting

- **Won’t open**
  - Multiblock is not formed (missing satellite / wrong facing)
- **Not mining**
  - Check status text: **No energy**, **Export full**, **Quarry dimension missing**
- **Doesn’t pick up water/lava**
  - Only **source** fluid blocks are collected, and only if the tank has space