Generate and install the app icon for /tmp/asmrlittle-steps.

Use the imagegen skill with the built-in image generation tool. The output must be filesystem-backed: after generation, copy or move the selected PNG from $CODEX_HOME/generated_images/... into:
/tmp/asmrlittle-steps/app/src/main/res/drawable/icon.png

Do not use ImageMagick, convert, SVG rasterization, gradients, text labels, launcher/mipmap assets, or hand-drawn placeholder artwork to create the design. ImageMagick may only resize an already generated image to exactly 512x512.
Do not push to GitHub, do not update Asana, and do not send Slack.

App name: ASMRLittle Steps
Type: shop
Primary color: #C65D4B

Required icon quality:
- PNG file at least 5 KB and exactly 512x512 pixels.
- Flat/vector-style logo on a solid opaque colored rounded-square canvas using the primary brand color.
- The canvas fills the icon bounds at the midpoint of each edge, but its four rounded corners are transparent.
- The central canvas and artwork are opaque; transparency is only outside the rounded canvas at the corners.
- Has thematic graphic elements beyond only letters.
- Not photorealistic and not a 3D render.
- If letters are present, all characters are sharp, uniform, and fully legible.
- Centered composition with complete, uncropped shapes.

The built-in image generator does not guarantee native transparency. Generate the rounded colored icon canvas surrounded by a perfectly flat #FF00FF chroma-key background, with no #FF00FF inside the icon. Copy the generated source from $CODEX_HOME/generated_images/..., then remove only the outer chroma-key background with:
`python "${CODEX_HOME:-$HOME/.codex}/skills/.system/imagegen/scripts/remove_chroma_key.py" --input <source> --out <alpha-output.png> --auto-key border --soft-matte --transparent-threshold 12 --opaque-threshold 220 --despill --force`
If necessary, resize the resulting generated PNG to exactly 512x512. Do not add, draw, or replace artwork during resizing.

Use this prompt content:
```text
professional 512 by 512 vector app icon, bold high-contrast lettering "AS" combined with shopping bag, price tag, storefront accent, product silhouettes integrated into the design, solid opaque colored rounded-square canvas using #C65D4B, clearly rounded app-icon corners with transparent pixels outside the rounded canvas, perfectly centered composition, all shapes complete and uncut, clean flat vector illustration, sharp and fully legible typography, commercial app icon quality

Negative prompt:
white background, fully transparent canvas, opaque square corners, sharp square tile, photo realistic, photograph, realistic rendering, 3D render, garbled text, illegible letters, deformed letterforms, letters only on plain background, monogram without graphics, off-center, cropped elements, incomplete shapes, blurry, low quality, pixelated, watermark, sketch, rough edges, drop shadow

```



After saving icon.png, validate it with `bash /home/codex-agent/codex-app-agent/validate-icon.sh /tmp/asmrlittle-steps/app/src/main/res/drawable/icon.png`.
