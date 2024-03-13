import React, { useEffect, useState } from "react";
import "./MainPageSection.scss";

function MainPageSection({
  section,
  children,
}: {
  section: string;
  children?: React.ReactNode;
}) {
  return (
    <div className="MainPageSection --container-wrapper mx-auto xl:max-w-[1200px]">
      <p className="heading">{section}</p>
      {children}
    </div>
  );
}

export default MainPageSection;
